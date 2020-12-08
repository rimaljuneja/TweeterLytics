package actors;

import models.Tweet;
import models.TweetSearchResult;
import models.TweetWordStatistics;
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
 * Actor class for word level statistics for the search term
 * 
 * @author Pavit S
 * @version 1.0.0
 */
public class WordLevelStatsActor extends AbstractActor {

	// Acts as user level cache
	private Map<String, TweetWordStatistics> searchHistory = new HashMap<>();

	private final ActorRef webSocket;

	private final TweetService tweetService;

	private final TwitterApi twitterApi;

	private final Map<String, Integer> wordfrequency;

	/**
	 * Constructor to create instance of this actor.
	 * 
	 * @param webSocket
	 * @param twitterApi    TwitterAPI Implementation
	 * @param tweetService
	 * @param wordfrequency
	 * @author Pavit S
	 */
	public WordLevelStatsActor(final ActorRef webSocket, final TwitterApi twitterApi, final TweetService tweetService,
			final Map<String, Integer> wordfrequency) {
		this.webSocket = webSocket;
		this.twitterApi = twitterApi;
		this.tweetService = tweetService;
		this.wordfrequency = wordfrequency;
	}

	/**
	 * Factory method to create instance of Word Stats Actor
	 * 
	 * @param webSocket
	 * @param twitterApi    TwitterAPI Implementation
	 * @param tweetService
	 * @param wordfrequency
	 * @return Props
	 * @author Pavit S
	 */
	public static Props props(final ActorRef webSocket, final TwitterApi twitterApi, final TweetService tweetService,
			final Map<String, Integer> wordfrequency) {
		return Props.create(WordLevelStatsActor.class, webSocket, twitterApi, tweetService, wordfrequency);
	}

	/**
	 * It registers reference of current actor to TimeActor
	 * 
	 * @author Pavit S
	 */
	@Override
	public void preStart() {

		context().actorSelection("/user/timeActor/").tell(new TimeActor.RegisterMsg(), self());
	}

	/**
	 * It de-registers reference of current actor from TimeActor
	 * 
	 * @author Pavit S
	 */
	@Override
	public void postStop() {

		context().actorSelection("/user/timeActor/").tell(new TimeActor.DeRegisterMsg(), self());
	}

	/**
	 * It receives messages and decides action based on message type.
	 * 
	 * @author Pavit S
	 */
	@Override
	public Receive createReceive() {
		return receiveBuilder()

				.match(PushNewData.class, pd -> sendUpdatedData())
				.match(ObjectNode.class, searchObject -> sendNewData(searchObject.get("keyword").textValue())).build();
	}

	/**
	 * This method sends new search data when queried by user.
	 * 
	 * @param keyword
	 * @author Pavit S
	 */
	private void sendNewData(final String keyword) {

		// Check if tweet is available in search hisory -- Acts like cache
		if (searchHistory.containsKey(keyword.toLowerCase())) {

			// Conversion of final TweetSearchResultObject object into JSON format
			JsonNode jsonObject = Json.toJson(searchHistory.get(keyword.toLowerCase()));

			// Send requested data to user via websocket
			webSocket.tell(Util.createResponse(jsonObject, true), self());
		}

		else {

			// Get tweets via keyword passed
			twitterApi.searchForKeywordAndGetTweets(keyword)

					// Get word level statistics for each search term
					.thenComposeAsync(tweets -> tweetService.getWordLevelStatistics(tweets))

					.thenAcceptAsync(response -> {

						// Add data to search history
						searchHistory.putIfAbsent(keyword.toLowerCase(), response);

						// Conversion of final TweetWordStatisticsResultObject object into JSON format
						JsonNode jsonObject = Json.toJson(response);

						// Send requested data to user via websocket
						webSocket.tell(Util.createResponse(jsonObject, true), self());

					});
		}

	}

	/**
	 * This method send new stats data if available to users via websocket.
	 * 
	 * @author Pavit Srivatsan
	 */
	private void sendUpdatedData() {
		searchHistory.keySet().parallelStream().forEach(keyword -> {

			// Get tweets via keyword passed
			twitterApi.searchForKeywordAndGetTweets(keyword).thenAcceptAsync(tweets -> {

				// get word level statistics of new tweets
				tweetService.getWordLevelStatistics(tweets).thenAcceptAsync(response -> {

					// Replace new data in searchHistory
					searchHistory.replace(keyword, response);

					response.setIsNewData(true);

					// Conversion of final TweetWordStatisticsResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);

					// Send new data to user
					webSocket.tell(Util.createResponse(jsonObject, true), self());

				});

			});
		});

	}

}
