package models;
/**
 * Model class for Twitter word Sentiment
 * @author Azim Surani 
 */

import java.util.List;

public class TweetSearchResult {
	
	private String keyword;
	
	private List<Tweet> tweets;
	
	private String sentiment;
	
	/**
	 * Construtor to create Tweet sentiment object from API data
	 * @param keyword
	 * @param tweets
	 * @param sentiment
	 * @author Azim Surani 
	 */

	public TweetSearchResult(String keyword, List<Tweet> tweets, String sentiment) {
		this.keyword = keyword;
		this.tweets = tweets;
		this.sentiment = sentiment;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

}
