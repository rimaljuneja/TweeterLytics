package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.Tweet;

import play.cache.AsyncCacheApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.TweetService;
import utils.Util;

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
	
	private Map<String,Integer> wordMap = new HashMap<>();

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
			
			input.parallel().forEach(word -> wordMap.put(word, 1));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		path = Paths.get("Wordlist/negative-words.txt");

		try (Stream<String> input = Files.lines(path))  {
			
			input.parallel().forEach(word -> wordMap.put(word, -1));
			
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
		
		return CompletableFuture.supplyAsync(()->ok(views.html.index.render()));
	}

	/**
	 * This method returns the latest 10 tweets containg the provided search keyword
	 * @param keyword
	 * @return CompletionStage<Result>
	 * @author Everyone
	 */
	public CompletionStage<Result> getTweetsBySearch(final String keyword){

		CompletionStage<List<Tweet>> cachedTweets = cache.getOrElseUpdate(keyword.toLowerCase(),
				() -> TweetService.searchForKeywordAndGetTweets(keyword),
				60*15); //Stores tweets in cache for 15 mins expiration time

		return cachedTweets.thenComposeAsync(tweets->
		
					// This method return the final response containing TweetSearchResultObject
					TweetService.getSentimentForTweets(tweets,keyword,wordMap)
				
				).thenApplyAsync(response-> {
					
					// Coversion of final TweetSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);

					return ok(Util.createResponse(jsonObject, true));
				
				},ec.current());

	}

}
