package actors;

import models.Tweet;
import models.TweetSearchResult;
import play.libs.Json;
import services.TweetService;
import utils.Util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.TimeActor.PushNewData;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;


/**
 * Actor class for tweets searched by Users and their sentiment.
 * 
 * @author Azim Surani
 * @version 1.0.0
 */
public class UserSearchActor extends AbstractActor{
	
	// Acts as user level cache
	private Map<String,TweetSearchResult> searchHistory = new HashMap<>();
	
	private final ActorRef webSocket;
	
	private final TweetService tweetService;
	
	private final Map<String,Integer> wordMap;

    /**
     * Constructor to create instance of this actor.
     * @param webSocket
     * @param tweetService
     * @param wordMap
     * @author Azim Surani
     */
    public UserSearchActor(final ActorRef webSocket,final TweetService tweetService,final Map<String,Integer> wordMap) {
    	this.webSocket =  webSocket;
    	this.tweetService = tweetService;
    	this.wordMap = wordMap;
    }

    /**
     * Factory method to create instance of User Search Actor
     * @param webSocket
     * @param tweetService
     * @param wordMap
     * @return Props
     * @author Azim Surani
     */
    public static Props props(final ActorRef webSocket,final TweetService tweetService,final Map<String,Integer> wordMap) {
        return Props.create(UserSearchActor.class, webSocket,tweetService,wordMap);
    }
    
    /**
     * It registers reference of current actor to TimeActor
     * 
     * @author Azim Surani
     */
    @Override
    public void preStart() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.RegisterMsg(), self());
    }
    
    /**
     * It de-registers reference of current actor from TimeActor
     * 
     * @author Azim Surani
     */
    @Override
    public void postStop() {
  
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.DeRegisterMsg(), self());
    }

	/**
	 * It reeceives messages and decides action based on message type.
	 * 
	 * @author Azim Surani
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				
				.match(PushNewData.class, pd -> sendUpdatedData())
				.match(ObjectNode.class, searchObject -> sendNewData(searchObject.get("keyword").textValue()))
				.build();
	}

	
	/**
	 * This method sends new search data when queried by user.
	 * 
	 * @param keyword
	 * @author Azim Surani
	 */
	private void sendNewData(final String keyword) {
		
		// Check if tweet is available in search hisory -- Acts like cache
		if(searchHistory.containsKey(keyword.toLowerCase())) {
			
			// Conversion of final TweetSearchResultObject object into JSON format
			JsonNode jsonObject = Json.toJson(searchHistory.get(keyword.toLowerCase()));
			
			// Send requested data to user via websocket
			webSocket.tell(Util.createResponse(jsonObject, true), self());
		}
			
		else {
			
			//Get tweets via keyword passed
			tweetService.searchForKeywordAndGetTweets(keyword)
			
				// Get Tweet Sentiment
				.thenComposeAsync(tweets->	tweetService.getSentimentForTweets(tweets,keyword,wordMap))
			
				.thenAcceptAsync(response -> {
					
					// Add data to search history
					searchHistory.putIfAbsent(keyword.toLowerCase(), response);
				
					// Conversion of final TweetSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);
					
					// Send requested data to user via websocket
					webSocket.tell(Util.createResponse(jsonObject, true), self());
				
				});
		}
		
	}
	
	/**
	 * This method send new tweets data if available to users via websocket.
	 * 
	 * @author Azim Surani
	 */
	private void sendUpdatedData() {
		
		searchHistory.keySet().parallelStream()
		.forEach(keyword -> {
			
			//Get tweets via keyword passed
			tweetService.searchForKeywordAndGetTweets(keyword)
			.thenAcceptAsync(tweets->
			{
				
				// The recent old tweet data - It will help to remove duplicate tweets already served.
				final String recentOldTweet = searchHistory.get(keyword).getTweets().get(0).getTweetText();
				
				//Check if first tweet of old and new data is not same
				if(!tweets.get(0).getTweetText().equals(recentOldTweet)) {
					
					//get sentiment of new tweets
					tweetService.getSentimentForTweets(tweets,keyword,wordMap)
					.thenAcceptAsync(response -> {
					
						int countOfNewTweets = 0;
						
						// Count how many tweets are actually new
						for(Tweet newTweet : response.getTweets()) {
							if(newTweet.getTweetText().equals(recentOldTweet))
								break;
							countOfNewTweets++;
						}
						
						// Replace new data in searchHistory
						searchHistory.replace(keyword,(TweetSearchResult)response.clone());
						
						// Keep only new tweets
						response.setTweets(response.getTweets().subList(0,countOfNewTweets));
						
						// Flag to identify that new data was sent
						response.setIsNewData(true);
						
						// Conversion of final TweetSearchResultObject object into JSON format
						JsonNode jsonObject = Json.toJson(response);
						
						// Send new data to user
						webSocket.tell(Util.createResponse(jsonObject, true), self());
						
					});

				}

			});
		});
			
	}

}