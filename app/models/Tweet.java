package models;

/**
 * It is the model class for handling Tweet Data
 * 
 * @author Everyone
 * @version 1.0
 */
public class Tweet {
	
	private String tweetText;
	
	private String userScreenName;

	/**
	 * Construtor to create Tweet object from api data
	 * @param tweetText
	 * @param userScreenName
	 * @param userid
	 * @author Everyone
	 */
	public Tweet(String tweetText, String userScreenName) {
		
		this.tweetText = tweetText;
		
		this.userScreenName = userScreenName;

	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	
}
