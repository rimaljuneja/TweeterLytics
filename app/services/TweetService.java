package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetService {

	public static List<Tweet> searchForKeywordAndGetTweets(String keyword){

		List<Tweet> searchResults = new ArrayList<>();

		Twitter twitter = TwitterFactory.getSingleton();

		Query query = new Query("\\\"" +keyword+ "\\\"" + " +exclude:retweets");
		
		// To get Recent Tweets first
		query.resultType(Query.RECENT);

		//count should be changed to 50 for final demo  (50*5=250)
		query.setCount(1);

		int count = 0;

		QueryResult result = null;

		do {

			try {

				result = twitter.search(query);

				searchResults.addAll(result.getTweets().
						parallelStream().
						map(status->
							new Tweet(	status.getText(),
										status.getUser().getScreenName(), 
										status.getUser().getId())).
						collect(Collectors.toList()));

				count += 1;

				if(count==5)
					break;

			} catch (TwitterException e) {
				e.printStackTrace();
			}

		}
		while( result!=null && (query = result.nextQuery()) !=null);

		return searchResults;
	}

}
