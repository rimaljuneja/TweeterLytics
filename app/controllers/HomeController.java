package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import models.Tweet;

import play.libs.Json;
import play.mvc.*;
import services.TweetService;
import utils.Cache;
import utils.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.UserTimelineResult;
import services.ProfileService;



/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author HGG02
 * @version 1.0.0
 */
public class HomeController extends Controller {
	
	private TweetService tweetService;
	
	private Cache cache;
	
	private Map<String,Integer> wordMap = new HashMap<>();
	

	@Inject
	public HomeController(TweetService tweetService,Cache cache) {
		this.tweetService = tweetService;
		this.cache = cache;
		initializeWordList();
	}
	
	/**
	 * Initializes wordlist with positive and negative words for sentiment analysis.
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
	 * Fetch WordMap for sentiment Analysis
	 * @return WordMap
	 * @author Azim Surani
	 */
	public Map<String,Integer> getWordMap() {
		return this.wordMap;
	}
	

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 * @author HGG02
	 */
	public CompletionStage<Result> index() {

		return CompletableFuture.supplyAsync(()->ok(views.html.index.render()));
	}

	/**
	 * Returns the latest 10 tweets containing the provided search keyword
	 * @param keyword
	 * @return 10 latest tweets corresponding to the search term
	 * @author HGG02
	 */
	public CompletionStage<Result> getTweetsBySearch(final String keyword){

		CompletionStage<List<Tweet>> cachedTweets = cache.getOrElseUpdate(keyword.toLowerCase(),
				()-> tweetService.searchForKeywordAndGetTweets(keyword)
				); //Stores tweets in cache

		return cachedTweets.thenComposeAsync(tweets->

					// This method return the final response containing TweetSearchResultObject
					tweetService.getSentimentForTweets(tweets,keyword,wordMap)

				).thenApplyAsync(response-> {

					// Conversion of final TweetSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);

					return ok(Util.createResponse(jsonObject, true));

				});

	}

	/**
	 * Returns word level statistics for the tweets corresponding to the
	 * entered search term
	 * 
	 * @param keyword
	 * @return Statistics of the word frequency in descending order
	 * @author Pavit Srivatsan
	 */
	public CompletionStage<Result> getStatisticsForSearchTerm(final String keyword) {

		CompletionStage<List<Tweet>> cachedTweets = cache.getOrElseUpdate(keyword.toLowerCase(),
				()-> tweetService.searchForKeywordAndGetTweets(keyword)); //Stores tweets in cache
		
		return cachedTweets.thenComposeAsync(tweets ->
		
				// This method return the final response containing TweetSearchResultObject
				tweetService.getWordLevelStatistics(tweets)

				).thenApplyAsync(response -> {

					// Conversion of final TweetSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);

					return ok(Util.createResponse(jsonObject, true));

				});

	}
	/**
	 * Displays the respective user profile with its 10 recent tweets 
	 * in a new Webpage
	 * by clicking on any username from the tweets on main search page.
	 * 
	 * @param userName
	 * @return User Profile including username and its 10 latest tweets 
	 * @author Rimal Juneja
	 */

	public CompletionStage<Result> getUserTimeline(final String userName) {

		return ProfileService.getUserTimelineByID(userName).thenApplyAsync((userTweets)->{
			

			UserTimelineResult response = new UserTimelineResult(userName, userTweets.subList
					(0, userTweets.size() < 10 ? userTweets.size() : 10));

			JsonNode jsonObject = Json.toJson(response);
			

			return ok(Util.createResponse(jsonObject, true));
		});
	}
	
	/**
	 * Returns latest 10 tweets as per the provided search hashtag
	 * @param hashtag
	 * @return 10 tweets with the corresponding hashtag
	 * @author Aayush Khandelwal
	 */
	public CompletionStage<Result> getTweetByHashtag(final String hashtag){
		
		CompletionStage<List<Tweet>> cachedTweets = cache.getOrElseUpdate(hashtag.toLowerCase(),
				()-> tweetService.searchForKeywordAndGetTweets(hashtag)); //Stores tweets in cache

		return  cachedTweets.thenComposeAsync(tweets->

		// This method return the final response containing TweetHashtagSearchResultObject
		tweetService.getHashtagForTweets(tweets,hashtag)

				).thenApplyAsync(response-> {

					// Conversion of final TweetHashtagSearchResultObject object into JSON format
					JsonNode jsonObject = Json.toJson(response);

					return ok(Util.createResponse(jsonObject, true));

				});

	}

}