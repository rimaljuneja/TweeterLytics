package services;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import models.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Twitter API Implentation for live mode
 * @author Azim Surani
 * @version 1.0.0
 */
public class TwitterImpl implements TwitterApi{

	private Twitter twitter;

	/**
	 * Constructor method to initialize TweetService object
	 * @author HGG02
	 */
	public TwitterImpl() {
		/*
		Initializing twitter object, 
	 	so that authentication is done only once when object is created*/
		twitter = TwitterFactory.getSingleton();
	}
	
	/**
	 * It calls the twitter api and fetches latest 100 Tweets
	 * @param keyword Search keyword
	 * @return CompletionStage containing List of Tweets
	 * @author HGG02
	 */
	public CompletionStage<List<Tweet>> searchForKeywordAndGetTweets(final String keyword){
		
		return supplyAsync (()->{

			List<Tweet> searchResults = new ArrayList<>();

			//Searches for exact phrase and excludes retweets to avoid duplication
			Query query = new Query("\\\"" +keyword+ "\\\"" + " +exclude:retweets");

			// To get Recent Tweets first
			query.resultType(Query.RECENT);

			//Fetches First 100 Tweets
			query.setCount(100);

			QueryResult result = null;

			try {

				result = twitter.search(query);

				searchResults.addAll(result.getTweets().
						parallelStream().
						map(status->
						new Tweet(	status.getText(),
								status.getUser().getScreenName()
								)).
						collect(Collectors.toList()));

			} catch (TwitterException e) {
				
				e.printStackTrace();
				
			}

			return searchResults;
			
		});
	}
	
	/**
	 * It calls the twitter api and fetches latest 10 Tweets
	 * @param userName Search userTimeline
	 * @return CompletionStage containing List of Tweets
	 * @author Rimal Juneja
	 */
	public   CompletableFuture<List<Tweet>> getUserTimelineByID(String userName){
		return supplyAsync(()->{
		List<Tweet> searchResults = new ArrayList<>();

		Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = new ArrayList<>();

		try {
            
            statuses = twitter.getUserTimeline(userName);
            searchResults.addAll(statuses.
					parallelStream().
					map(status->
						new Tweet(	status.getText(),
									status.getUser().getScreenName()
									)).
					collect(Collectors.toList()));
           
          
           
        } catch (TwitterException te) {
            te.printStackTrace();
          
            System.exit(-1);
        }
		 return searchResults;
		});
	}

}
