package services;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import models.Tweet;
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

	public static CompletableFuture<List<Tweet>> getUserTimelineByID(String userName){
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
