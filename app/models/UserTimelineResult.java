package models;
/**
 *Model class for handling User Profile
 * @author Rimal Juneja 
 */

import java.util.List;

public class UserTimelineResult {
	
	private String userName;
	
	private List<Tweet> tweets;
	
	private Boolean isNewData = false;

	/**
	 * Constructor to create User Profile object from API data
	 * @param userName
	 * @param tweets
	 * @author Rimal Juneja
	 */
	public UserTimelineResult(String userName, List<Tweet> tweets) {
		this.userName = userName;
		this.tweets = tweets;
		this.isNewData = false;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new UserTimelineResult(this.userName, this.tweets);
		}
	}

	public String getuserName() {
		return userName;
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
