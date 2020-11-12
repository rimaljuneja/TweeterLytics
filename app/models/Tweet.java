package models;

/**
 * Model class for handling Tweet data
 * 
 * @author HGG02
 * @version 1.0
 */
public class Tweet {
	
	private String tweetText;
	
	private String userScreenName;

	/**
	 * Construtor to create Tweet object from API data
	 * @param tweetText
	 * @param userScreenName
	 * @author HGG02
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
	
//	public String toString() {
//		return "new Tweet(\""+this.tweetText.replace("\n", "").replace("\"", "\\\"")+"\",\""+this.userScreenName+"\")";
//	}
	
}
