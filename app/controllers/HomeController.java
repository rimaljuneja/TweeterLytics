package controllers;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import akka.Done;
import models.Tweet;
import models.TweetSearchResult;
import play.cache.AsyncCacheApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.TweetService;
import utils.Util;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private HttpExecutionContext ec;
	
	private Map<String,Integer> wordlist = new HashMap<>();

	private AsyncCacheApi cache;

	@Inject
	public HomeController(HttpExecutionContext ec,AsyncCacheApi cache) {
		this.ec = ec;
		this.cache = cache;	
		initializeWordList();
	}
	
	/**
	 * This method initializes wordlist with positive and negative words for sentiment analysis.
	 * @author Azim Surani
	 */
	private void initializeWordList() {
		
		Path path = Paths.get("Wordlist/positive-words.txt");

		try (Stream<String> input = Files.lines(path))  {
			input.parallel().forEach(word -> wordlist.put(word, 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		path = Paths.get("Wordlist/negative-words.txt");

		try (Stream<String> input = Files.lines(path))  {
			input.parallel().forEach(word -> wordlist.put(word, -1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public CompletionStage<Result> index() {
		
		CompletionStage<Done> resultAll = cache.removeAll();
		
		return resultAll.thenCombine(supplyAsync(()->
					ok(views.html.index.render()),ec.current()),(d,ok)-> ok);
	}

	/**
	 * @param keyword
	 * @return
	 */
	public CompletionStage<Result> getTweetsBySearch(String keyword){
		
		CompletionStage<List<Tweet>> cachedTweets = cache.getOrElseUpdate(keyword,
				() -> TweetService.searchForKeywordAndGetTweets(keyword),
				60*15);
		
		return cachedTweets.thenApplyAsync(tweets-> {
			
			/*
			  All the below oprations are non-blocking, so we are not using
			  seperate thenApply for each of them instead we have combined 
			  them into single thread operation
			*/
	
			// Get the sentiment from tweets @author - Azim Surani
			String sentiment = TweetService.getSentimentForTweets(tweets, wordlist);

			TweetSearchResult response = new TweetSearchResult(keyword, tweets.subList(0, tweets.size() < 10 ? tweets.size() : 10) , sentiment);

			JsonNode jsonObject = Json.toJson(response);
	
			return ok(Util.createResponse(jsonObject, true));
			
		},ec.current());

	}

}
