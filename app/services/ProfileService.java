package services;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import models.Tweet;
import models.TweetHashtagSearchResult;
import models.UserTimelineResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * This Service class uses the Twitter API to fetch the user related information 
 * including username and its respective tweets
 * 
 * @author Rimal Juneja
 *
 */
public class ProfileService {

	
	
	
	public CompletableFuture<UserTimelineResult>  getTweetsByUserName(final List<Tweet> tweets,final String username) {

		return supplyAsync (()->{
			
			return new UserTimelineResult(username, tweets.subList(0, tweets.size() < 10 ? tweets.size() : 10));

		});
	}
	
	
}
