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
	 * @param tweetText Tweet
	 * @param userScreenName Username
	 * @author HGG02
	 */
	public Tweet(String tweetText, String userScreenName) {
		
		this.tweetText = tweetText;
		
		this.userScreenName = userScreenName;

	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new Tweet(this.tweetText, this.userScreenName);
		}
	}


	public String getTweetText() {
		return tweetText;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	
}
