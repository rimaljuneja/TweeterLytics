package actors;

import models.Tweet;

import models.UserTimelineResult;
import play.libs.Json;

import services.ProfileService;
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
 * Actor class for User timeline.
 * 
 * @author Rimal Juneja
 * @version 1.0.0
 */
public class UserTimelineActor extends AbstractActor{
	
	// Acts as user level cache
	private Map<String,UserTimelineResult> searchHistory = new HashMap<>();
	
	private final ActorRef webSocket;
	
	private final TwitterApi twitterApi;
	
	private final ProfileService ProfileService;
	
	
	/**
	 * Constructor to create instance of this actor.
	 * @param webSocket
	 * @param twitterApi TwitterAPI Implementation
	 * @param ProfileService
	 * @author Rimal Juneja
	 */
    public UserTimelineActor(final ActorRef webSocket,final TwitterApi twitterApi, final ProfileService ProfileService) {
    	this.webSocket =  webSocket;
    	this.twitterApi = twitterApi;
    	this.ProfileService = ProfileService;
    }

    /**
     * Factory method to create instance of User Search Actor
     * @param webSocket
     * @param twitterApi
     * @param ProfileService 
     * @return Props
     * @author Rimal Juneja
    
     */
    public static Props props(final ActorRef webSocket,final TwitterApi twitterApi, final ProfileService ProfileService) {
        return Props.create(UserTimelineActor.class,webSocket,twitterApi,ProfileService);
    }
    
    /**
     * It registers reference of current actor to TimeActor
     * 
     * @author Rimal Juneja
     */
    @Override
    public void preStart() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.RegisterMsg(), self());
    }
    
    /**
     * It de-registers reference of current actor from TimeActor
     * 
     * @author Rimal Juneja
     */
    @Override
    public void postStop() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.DeRegisterMsg(), self());
    }

	/**
	 * It reeceives messages and decides action based on message type.
	 * 
	 * @author Rimal Juneja
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				
				.match(PushNewData.class, pd -> sendUpdatedData())
				.match(ObjectNode.class, searchObject -> sendNewData(searchObject.get("userName").textValue()))
				.build();
	}

	
	/**
	 * This method sends new search data(tweets) of user.
	 * 
	 * @param userName
	 * @author Rimal Juneja
	 */
	private void sendNewData(final String userName) {
		
		// Check if username is available in search history -- Acts like cache
		if(searchHistory.containsKey(userName)) {
			
			// Conversion of final TweetSearchResultObject object into JSON format
			JsonNode jsonObject = Json.toJson(searchHistory.get(userName));
			
			// Send requested data to user via websocket
			webSocket.tell(Util.createResponse(jsonObject, true), self());
		}
			
		else {
			
			//Get userProfile via username 
			twitterApi.getUserTimelineByID(userName)
			.thenComposeAsync(tweets->	ProfileService.getTweetsByUserName(tweets,userName))
				.thenAcceptAsync(response -> {
					
					// Add data to search history
					searchHistory.putIfAbsent(userName,  response);
				
					// Conversion of final UserTimelineResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);
					
					// Send requested data to user via websocket
					webSocket.tell(Util.createResponse(jsonObject, true), self());
				
				});
		}
		
	}
	
	/**
	 * This method send new tweets data of the user if available  via websocket.
	 * 
	 * @author Rimal Juneja
	 */
	private void sendUpdatedData() {
		
		
		
		searchHistory.keySet().parallelStream()
		.forEach(userName -> {
			
			//Get tweets of username passed
			twitterApi.getUserTimelineByID(userName)
			.thenAcceptAsync(tweets->
{
				
				// The recent old tweet data - It will help to remove duplicate tweets already served.
				final String recentOldTweet = searchHistory.get(userName).getTweets().get(0).getTweetText();
				
				//Check if first tweet of old and new data is not same
				if(!tweets.get(0).getTweetText().equals(recentOldTweet)) {
					
					//get new tweets by hashtag
					ProfileService.getTweetsByUserName(tweets,userName)
					.thenAcceptAsync(response -> {
					
						int countOfNewTweets = 0;
						
						// Count how many tweets are actually new
						for(Tweet newTweet : response.getTweets()) {
							if(newTweet.getTweetText().equals(recentOldTweet))
								break;
							countOfNewTweets++;
						}
						
						// Replace new data in searchHistory
						searchHistory.replace(userName,(UserTimelineResult) response.clone());
						
						// Keep only new tweets
						response.setTweets(response.getTweets().subList(0,countOfNewTweets));
						
						// Flag to identify that new data was sent
						response.setIsNewData(true);
						
						// Conversion of final UserTimelineResultObject object into JSON format
						JsonNode jsonObject = Json.toJson(response);
						
						// Send new data to user
						webSocket.tell(Util.createResponse(jsonObject, true), self());
						
					});

				}

			});
		});
			
	}

	

}