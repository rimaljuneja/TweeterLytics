package models;
/**
 * Model class for Twitter word Statistics
 * @author Pavit Srivatsan
 */

import java.util.List;
import java.util.Map;

public class TweetWordStatistics {
	private Boolean isNewData;
	
	public Map<String, Integer> wordfrequency;
	
	private List<Tweet> tweets;
	/**
	 * Construtor to create Tweet sentiment object from API data
	 * @param wordfrequency
	 * @author Pavit Srivatsan
	 */
	public TweetWordStatistics( Map<String, Integer> wordfrequency) {
		this.wordfrequency = wordfrequency;
		this.isNewData = false;
		this.tweets = tweets;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new TweetWordStatistics(this.wordfrequency);
		}
	}
	
	public Boolean getIsNewData() {
		return isNewData;
	}

	public void setIsNewData(Boolean isNewData) {
		this.isNewData = isNewData;
	}
	
	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

}