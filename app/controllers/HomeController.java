package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.Tweet;
import models.TweetSearchResult;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.TweetService;
import utils.Util;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private HttpExecutionContext ec;

	@Inject
	public HomeController(HttpExecutionContext ec) {
		this.ec = ec;
	}



	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public Result index() {

		return ok(views.html.index.render());
	}
	
	public CompletionStage<Result> getTweetsBySearch(String keyword){
		
		return supplyAsync(()->{
			
			List<Tweet> tweets = TweetService.searchForKeywordAndGetTweets(keyword);
			
			// Need cache implementaion
			// Need Sentiment analysis
			
			TweetSearchResult response = new TweetSearchResult(keyword, tweets, "neutral");
			
			JsonNode jsonObjects = Json.toJson(response);
			
            return ok(Util.createResponse(jsonObjects, true));
			
		},ec.current());
		
	}

}
