package models;

import java.util.List;

public class UserTimelineResult {
	
	private String userId;
	
	private List<Tweet> tweets;

	public UserTimelineResult(String userId, List<Tweet> tweets) {
		this.userId = userId;
		this.tweets = tweets;
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

}
