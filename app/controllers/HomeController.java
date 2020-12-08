package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import actors.TimeActor;
import actors.UserSearchActor;
import actors.UserSearchHashtagActor;
import actors.WordLevelStatsActor;
import actors.UserTimelineActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;

import play.libs.Json;
import play.libs.streams.ActorFlow;
import play.mvc.*;
import services.TweetService;
import services.TwitterApi;
import utils.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.UserTimelineResult;
import services.ProfileService;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 * 
 * @author HGG02
 * @version 1.0.0
 */
public class HomeController extends Controller {

	private TweetService tweetService;

	private ProfileService profileService;

	private Map<String, Integer> wordMap = new HashMap<>();

	private final Map<String, Integer> wordfrequency = new HashMap<>();;

	private ActorSystem actorSystem;

	@Inject
	private Materializer materializer;

	@Inject
	private TwitterApi twitterApi;

	@Inject
	public HomeController(TweetService tweetService, ProfileService profileTimelineService, ActorSystem actorSystem) {

		this.tweetService = tweetService;

		this.profileService = profileTimelineService;

		this.actorSystem = actorSystem;

		initializeWordList();

		actorSystem.actorOf(TimeActor.props(), "timeActor");
	}

	/**
	 * Initializes wordlist with positive and negative words for sentiment analysis.
	 * 
	 * @author Azim Surani
	 */
	private void initializeWordList() {

		Path path = Paths.get("Wordlist/positive-words.txt");

		try (Stream<String> input = Files.lines(path)) {

			input.parallel().forEach(word -> wordMap.put(word, 1));

		} catch (IOException e) {

			e.printStackTrace();

		}

		path = Paths.get("Wordlist/negative-words.txt");

		try (Stream<String> input = Files.lines(path)) {

			input.parallel().forEach(word -> wordMap.put(word, -1));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Fetch WordMap for sentiment Analysis
	 * 
	 * @return WordMap
	 * @author Azim Surani
	 */
	public Map<String, Integer> getWordMap() {
		return this.wordMap;
	}

	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 * 
	 * @return CompletionStage Result
	 * @author HGG02
	 */
	public CompletionStage<Result> index() {

		return CompletableFuture.supplyAsync(() -> ok(views.html.index.render()));

	}

	/**
	 * Creates websocket connection for main search page
	 * 
	 * @return WebSocket
	 * @author HGG02
	 */
	public WebSocket getTweetsBySearchViaWebSocket() {

		return WebSocket.Json.accept(request -> ActorFlow.actorRef(
				ws -> UserSearchActor.props(ws, twitterApi, tweetService, wordMap), actorSystem, materializer));

	}

	/**
	 * Creates websocket connection for hashtag search page
	 * 
	 * @return WebSocket
	 */
	public WebSocket getTweetsByHashtagViaWebSocket() {

		return WebSocket.Json.accept(request -> ActorFlow
				.actorRef(ws -> UserSearchHashtagActor.props(ws, twitterApi, tweetService), actorSystem, materializer));

	}

	/**
	 * Creates websocket connection for word level statistics for search terms
	 * 
	 * @return WebSocket
	 * @author Pavit Srivatsan
	 */
	public WebSocket getTweetStatisticsViaWebSocket() {

		return WebSocket.Json.accept(request -> ActorFlow.actorRef(
				ws -> WordLevelStatsActor.props(ws, twitterApi, tweetService, wordfrequency), actorSystem,
				materializer));

	}

	/**
	 * Creates websocket connection for UserTimeline page
	 * 
	 * @return WebSocket
	 */
	public WebSocket getUserTimelineViaWebSocket() {

		return WebSocket.Json.accept(request -> ActorFlow
				.actorRef(ws -> UserTimelineActor.props(ws, twitterApi, profileService), actorSystem, materializer));

	}

}