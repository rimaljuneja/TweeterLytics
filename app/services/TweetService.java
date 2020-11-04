package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author everyone
 *
 */
public class TweetService {

	/**
	 * @param keyword
	 * @return
	 */
	public static List<Tweet> searchForKeywordAndGetTweets(String keyword){

		List<Tweet> searchResults = new ArrayList<>();

		Twitter twitter = TwitterFactory.getSingleton();

		Query query = new Query("\\\"" +keyword+ "\\\"" + " +exclude:retweets");
		
		// To get Recent Tweets first
		query.resultType(Query.RECENT);

		//count should be changed to 50 for final demo  (50*5=250)
		query.setCount(50);

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
	
	/**
	 * It calculates the average sentiment of tweets passed.
	 * @param tweets "List of Tweets"
	 * @param wordMap "Word Map of postive and negative words"
	 * @return String sentiment
	 * @author Azim Surani
	 */
	public static String getSentimentForTweets(List<Tweet> tweets,Map<String,Integer> wordMap) {
		
		List<String> listOfTweetSentiments = tweets.parallelStream().map(tweet -> {
			
			//replace # by space
			String tweetText = tweet.getTweetText().replace("#", " ");
			
			//seperate all words
			String[] words = tweetText.trim().split("\\s+");
			
			//Rrelative tweet sentiment (E.g 2 positive words and 1 negative word then o/p would be 2-1=1... overall positive )
			Integer sentiment = Arrays.stream(words).map((String word) -> wordMap.getOrDefault(word.toLowerCase() , 0)).reduce(0,(a,b)-> a+b);
			
			if(sentiment>0)
				return "1";
			else if(sentiment==0)
				return "0";
			else
				return "-1";
		}).collect(Collectors.toList());
		
		//Count of positive tweets
		int countOfPositiveTweets = Collections.frequency(listOfTweetSentiments,"1");
		
		//Count of negative tweets
		int countOfNegativeTweets = Collections.frequency(listOfTweetSentiments,"-1");
		
		if((float)countOfPositiveTweets/tweets.size()>=0.7F)
			return "happy"; // Return happy if positive tweets are >= 70%
		
		else if((float)countOfNegativeTweets/tweets.size()>=0.7F)
			return "sad"; // Return sad if negative tweets are >= 70%
		
		else
			return "neutral"; //Else return neutral
		
	}

}
