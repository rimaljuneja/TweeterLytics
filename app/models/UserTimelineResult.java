package models;
/**
 *Model class for handling User Profile
 * @author Rimal Juneja 
 */

import java.util.List;

public class UserTimelineResult {
	
	private String userName;
	
	private List<Tweet> tweets;

	/**
	 * Construtor to create User Profile object from API data
	 * @param userName
	 * @param tweets
	 * @author Rimal Juneja
	 */
	public UserTimelineResult(String userName, List<Tweet> tweets) {
		this.userName = userName;
		this.tweets = tweets;
	}

	public String getuserName() {
		return userName;
	}


	public List<Tweet> getTweets() {
		return tweets;
	}

	

}
