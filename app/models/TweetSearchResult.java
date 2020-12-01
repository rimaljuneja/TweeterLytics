package models;
/**
 * Model class for Twitter word Sentiment
 * @author Azim Surani 
 * @version 1.0.0
 */

import java.util.List;

public class TweetSearchResult {
	
	private String keyword;
	
	private List<Tweet> tweets;
	
	private String sentiment;
	
	private Boolean isNewData;
	
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
