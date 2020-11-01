package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.Tweet;
import models.TweetSearchResult;

import play.cache.SyncCacheApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.TweetService;
import utils.Util;
import java.util.Optional;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private HttpExecutionContext ec;

	private List<String> cacheKeys;

	@Inject
	SyncCacheApi cache;

	@Inject
	public HomeController(HttpExecutionContext ec,SyncCacheApi cache) {
		this.ec = ec;
		this.cache = cache;	
		cacheKeys = new ArrayList<>();
	}

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public Result index() {
		
		if(!cacheKeys.isEmpty()) {
		
			//clear the cache and start new session for each refresh
			cacheKeys.parallelStream().forEach(key -> cache.remove(key));
		
			cacheKeys = new ArrayList<>();
		
		}

		return ok(views.html.index.render());
	}

	public CompletionStage<Result> getTweetsBySearch(String keyword){

		return supplyAsync(()->{

			Optional<List<Tweet>> cachedTweets = cache.get(keyword.toLowerCase());

			List<Tweet> tweets = new ArrayList<>();

			if(cachedTweets.isPresent()) {

				tweets = cachedTweets.get();

			}

			else {

				tweets = TweetService.searchForKeywordAndGetTweets(keyword);

				cache.set(keyword.toLowerCase(), tweets);

				cacheKeys.add(keyword.toLowerCase());

			}

			//Sentiment Analysis Code

			TweetSearchResult response = new TweetSearchResult(keyword, tweets.subList(0, tweets.size() < 10 ? tweets.size() : 10) , "neutral");

			JsonNode jsonObject = Json.toJson(response);

			return ok(Util.createResponse(jsonObject, true));


		},ec.current());

	}

}
