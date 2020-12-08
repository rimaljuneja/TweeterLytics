package actors;

import models.Tweet;
import models.TweetSearchResult;
import models.TweetHashtagSearchResult;
import play.libs.Json;
import services.TweetService;
import services.TwitterApi;
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
 * Actor class for tweets searched by hashtag.
 * 
 * @author Aayush Khandelwal
 * @version 1.0.0
 */
public class UserSearchHashtagActor extends AbstractActor{
	
	// Acts as user level cache
	private Map<String,TweetHashtagSearchResult> searchHistory = new HashMap<>();
	
	private final ActorRef webSocket;
	
	private final TweetService tweetService;
	
	private final TwitterApi twitterApi;
	

    /**
     * Constructor to create instance of this actor.
     * @param webSocket
     * @param twitterApi TwitterAPI Implementation
     * @param tweetService
     * @author Aayush Khandelwal
     */
    public UserSearchHashtagActor(final ActorRef webSocket,final TwitterApi twitterApi,final TweetService tweetService) {
    	this.webSocket =  webSocket;
    	this.twitterApi = twitterApi;
    	this.tweetService = tweetService;
    }

    /**
     * Factory method to create instance of User Search Hashtag Actor
     * @param webSocket
     * @param twitterApi TwitterAPI Implementation
     * @param tweetService
     * @return Props
     * @author Aayush Khandelwal
     */
    public static Props props(final ActorRef webSocket,final TwitterApi twitterApi,final TweetService tweetService) {
        return Props.create(UserSearchHashtagActor.class, webSocket,twitterApi,tweetService);
    }
    
    /**
     * It registers reference of current actor to TimeActor
     * 
     * @author Aayush Khandelwal
     */
    @Override
    public void preStart() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.RegisterMsg(), self());
    }
    
    /**
     * It de-registers reference of current actor from TimeActor
     * 
     * @author Aayush Khandelwal
     */
    @Override
    public void postStop() {
  
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.DeRegisterMsg(), self());
    }

	/**
	 * It receives messages and decides action based on message type.
	 * 
	 * @author Aayush Khandelwal
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				
				.match(PushNewData.class, pd -> sendUpdatedData())
				.match(ObjectNode.class, searchObject -> sendNewData(searchObject.get("hashtag").textValue()))
				.build();
	}

	
	/**
	 * This method sends new search data for hashtag when queried by user.
	 * 
	 * @param hashtag
	 * @author Aayush Khandelwal
	 */
	private void sendNewData(final String hashtag) {
		
		// Check if tweet is available in search hisory -- Acts like cache
		if(searchHistory.containsKey(hashtag.toLowerCase())) {
			
			// Conversion of final TweetHashtagSearchResultObject object into JSON format
			JsonNode jsonObject = Json.toJson(searchHistory.get(hashtag.toLowerCase()));
			
			// Send requested data to user via websocket
			webSocket.tell(Util.createResponse(jsonObject, true), self());
		}
			
		else {
			
			//Get tweets via hashtag passed
			twitterApi.searchForKeywordAndGetTweets(hashtag)
			
			.thenComposeAsync(tweets->	tweetService.getHashtagForTweets(tweets,hashtag))
			
				.thenAcceptAsync(response -> {
					
					// Add data to search history
					searchHistory.putIfAbsent(hashtag.toLowerCase(), response);
				
					// Conversion of final TweetSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);
					
					// Send requested data to user via websocket
					webSocket.tell(Util.createResponse(jsonObject, true), self());
				
				});
		}
		
	}
	
	/**
	 * This method send new tweets data for hashtag if available to users via websocket.
	 * 
	 * @author Aayush Khandelwal
	 */
	private void sendUpdatedData() {
		
		searchHistory.keySet().parallelStream()
		.forEach(hashtag -> {
			
			//Get tweets via hashtag passed
			twitterApi.searchForKeywordAndGetTweets(hashtag)
			.thenAcceptAsync(tweets->
			{
				
				// The recent old tweet data - It will help to remove duplicate tweets already served.
				final String recentOldTweet = searchHistory.get(hashtag).getTweets().get(0).getTweetText();
				
				//Check if first tweet of old and new data is not same
				if(!tweets.get(0).getTweetText().equals(recentOldTweet)) {
					
					//get new tweets by hashtag
					tweetService.getHashtagForTweets(tweets,hashtag)
					.thenAcceptAsync(response -> {
					
						int countOfNewTweets = 0;
						
						// Count how many tweets are actually new
						for(Tweet newTweet : response.getTweets()) {
							if(newTweet.getTweetText().equals(recentOldTweet))
								break;
							countOfNewTweets++;
						}
						
						// Replace new data in searchHistory
						searchHistory.replace(hashtag,(TweetHashtagSearchResult)response.clone());
						
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
