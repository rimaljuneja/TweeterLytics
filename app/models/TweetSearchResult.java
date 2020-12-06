package models;

import java.util.List;

/**
 * Model class for Tweet Search Result
 * @author Azim Surani 
 * @version 1.0.0
 */
public class TweetSearchResult {
	
	private String keyword;
	
	private List<Tweet> tweets;
	
	private String sentiment;
	
	private Boolean isNewData;
	
	/**
	 * Construtor to create Tweet Search Result
	 * @param keyword Search keyword
	 * @param tweets List of Tweet objects
	 * @param sentiment Final sentiment of tweets
	 * @author Azim Surani 
	 */

	public TweetSearchResult(String keyword, List<Tweet> tweets, String sentiment) {
		this.keyword = keyword;
		this.tweets = tweets;
		this.sentiment = sentiment;
		this.isNewData = false;
	}
	

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new TweetSearchResult(this.keyword, this.tweets, this.sentiment);
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public String getSentiment() {
		return sentiment;
	}

	public Boolean getIsNewData() {
		return isNewData;
	}

	public void setIsNewData(Boolean isNewData) {
		this.isNewData = isNewData;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}


}
