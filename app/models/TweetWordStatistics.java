package models;

import java.util.List;
import java.util.Map;

public class TweetWordStatistics {
	
	private List<Tweet> tweets;
	
	private Map<String, Integer> stringlength;

	public TweetWordStatistics( List<Tweet> tweets, Map<String, Integer> stringlength) {
		this.tweets = tweets;
		this.stringlength = stringlength;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Map<String, Integer> getStringLength() {
		return stringlength;
	}

	public void setStringLength(Map<String, Integer> stringlength) {
		this.stringlength = stringlength;
	}

}