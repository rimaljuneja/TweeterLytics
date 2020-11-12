package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import models.Tweet;
import models.TweetSearchResult;
import models.TweetHashtagSearchResult;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import models.TweetWordStatistics;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * @author HGG02
 *
 */
public class TweetService {
	
	private Twitter twitter;
	
	/**
	 * Constructor method to initialize TweetService object
	 */
	public TweetService() {
		/*
		Initializing twitter object, 
	 	so that authentication is done only once when object is created*/
		twitter = TwitterFactory.getSingleton();
	}

	/**
	 * @param keyword
	 * @return
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
	 * It calculates the average sentiment of tweets passed.
	 * @param tweets "List of Tweets"
	 * @param wordMap "Word Map of postive and negative words"
	 * @return CompletableFuture<TweetSearchResult> "Final TweetSearchResult Object including sentiment data"
	 * @author Azim Surani
	 */
	public CompletableFuture<TweetSearchResult>  getSentimentForTweets(final List<Tweet> tweets,final String keyword,final Map<String,Integer> wordMap) {

		return supplyAsync (()->{

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
			
			String sentiment = null;

			if((float)countOfPositiveTweets/tweets.size()>=0.7f)
				sentiment = "happy"; // Return happy if positive tweets are >= 70%

			else if((float)countOfNegativeTweets/tweets.size()>=0.7f)
				sentiment = "sad"; // Return sad if negative tweets are >= 70%

			else
				sentiment = "neutral"; //Else return neutral
			
			return new TweetSearchResult(keyword, tweets.subList(0, tweets.size() < 10 ? tweets.size() : 10) , sentiment);

		});
	}
	
	/**
	 * Calculates word level statistics for inputed tweet lists
	 * 
	 * @param tweets "List of Tweets"
	 * @author pavit.srivatsan
	 */
	public CompletableFuture<TweetWordStatistics>  getWordLevelStatistics(final List<Tweet> tweets) {
		
		return supplyAsync (()->{
			
			//Converting tweet list into list of strings
			List<String> newList = new ArrayList<>(tweets.size());
			for (Tweet mytweet : tweets) { 
				  newList.add(String.valueOf(mytweet.getTweetText())); 
				}
			
			//Splitting words
			List <String> list = Stream.of(newList.toString()).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
		            .collect(Collectors.toList());
			
			//Mapping words with their frequency 
			Map<String, Integer> wordsCountMap = list.stream().map(eachWord -> eachWord)
					.collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
			
			//Sorting the result in descending order
			wordsCountMap = wordsCountMap.entrySet()
					.stream()
					.sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)-> e1, LinkedHashMap::new));
			return new TweetWordStatistics(wordsCountMap);
		});
	}
	
	/**
	 * For getting tweet by hashtag
	 * @param tweets "List of Tweets"
	 * @param hastag
	 * @author Aayush Khandelwal
	 */
	
	public CompletableFuture<TweetHashtagSearchResult>  getHashtagForTweets(final List<Tweet> tweets,final String hashtag) {

		return supplyAsync (()->{
			
			return new TweetHashtagSearchResult(hashtag, tweets.subList(0, tweets.size() < 10 ? tweets.size() : 10));

		});
	}

}
