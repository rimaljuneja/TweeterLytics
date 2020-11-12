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
           
            System.out.println("Showing @" + userName + "'s user timeline.");
           for (Status status : statuses) {
               System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
		 return searchResults;
		});
	}
	
	
	
}
