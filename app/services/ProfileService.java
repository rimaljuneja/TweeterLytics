package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Tweet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ProfileService {

	public static List<Tweet> getUserTimelineeByID(String userId){

		List<Tweet> searchResults = new ArrayList<>();

		Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = new ArrayList<>();

		try {
            
            statuses = twitter.getUserTimeline(userId);
            searchResults.addAll(statuses.
					parallelStream().
					map(status->
						new Tweet(	status.getText(),
									status.getUser().getScreenName(), 
									status.getUser().getId())).
					collect(Collectors.toList()));
           
            System.out.println("Showing @" + userId + "'s user timeline.");
           for (Status status : statuses) {
               System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
		 return searchResults;
		
	}
	
	
	
}
