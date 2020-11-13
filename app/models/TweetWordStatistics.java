package models;
/**
 * Model class for Twitter word Statistics
 * @author Pavit Srivatsan
 */
import java.util.List;
import java.util.Map;

public class TweetWordStatistics {
	
	
	private Map<String, Integer> wordfrequency;

	/**
	 * Construtor to create Tweet sentiment object from API data
	 * @param wordfrequency
	 * @author Pavit Srivatsan
	 */
	public TweetWordStatistics( Map<String, Integer> wordfrequency) {
		this.wordfrequency = wordfrequency;
	}

}