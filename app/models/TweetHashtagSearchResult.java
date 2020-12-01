package models;


/**
 * Model class for handling Tweet data
 * 
 * @author Aayush Khandelwal 
 * @version 1.0.0
 */

import java.util.List;

public class TweetHashtagSearchResult {
	
	private String hashtag;
	
	private List<Tweet> tweets;
	
	/**
	 * Construtor to create Tweet object from API data
	 * @param hashtag
	 * @param tweets
	 * @author Aayush Khandelwal 
	 */
	public TweetHashtagSearchResult(String hashtag, List<Tweet> tweets) {
		this.hashtag = hashtag;
		this.tweets = tweets;
	}

	public String getHashtag() {
		return hashtag;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

}
