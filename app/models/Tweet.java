package models;

/**
 * It is the model class for handling Tweet Data
 * 
 * @author Everyone
 * @version 1.0
 */
public class Tweet {
	
	public String tweetText;
	
	private String userScreenName;
	
	private Long userid;

	/**
	 * Construtor to create Tweet object from api data
	 * @param tweetText
	 * @param userScreenName
	 * @param userid
	 * @author Everyone
	 */
	public Tweet(String tweetText, String userScreenName, Long userid) {
		
		this.tweetText = tweetText;
		
		this.userScreenName = userScreenName;
		
		this.userid = userid;
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
}
