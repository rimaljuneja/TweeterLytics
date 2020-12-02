package models;


/**
 * Model class for handling Tweet data by hashtag
 * 
 * @author Aayush Khandelwal 
 * @version 1.0.0
 */

import java.util.List;

public class TweetHashtagSearchResult {
	
	private String hashtag;
	
	private List<Tweet> tweets;
	
	private Boolean isNewData;
	
	/**
	 * Construtor to create Tweet object from API data
	 * @param hashtag
	 * @param tweets
	 * @author Aayush Khandelwal 
	 */
	public TweetHashtagSearchResult(String hashtag, List<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
		this.isNewData = false;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new TweetHashtagSearchResult(this.hashtag, this.tweets);
		}
	}

	public String getHashtag() {
		return hashtag;
	}

	public List<Tweet> getTweets() {
		return tweets;
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
