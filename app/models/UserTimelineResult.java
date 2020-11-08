package models;

import java.util.List;

public class UserTimelineResult {
	
	private String userId;
	
	private List<Tweet> tweets;
	
	private String sentiment;

	public UserTimelineResult(String userId, List<Tweet> tweets, String sentiment) {
		this.userId = userId;
		this.tweets = tweets;
		this.sentiment = sentiment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
