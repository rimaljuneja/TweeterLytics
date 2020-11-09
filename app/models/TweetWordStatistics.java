package models;
/**
 * Model class for Twitter word Statistics
 * @author pavit.srivatsan
 */
import java.util.List;
import java.util.Map;

public class TweetWordStatistics {
	
	
	private Map<String, Integer> wordfrequency;

	public TweetWordStatistics( Map<String, Integer> wordfrequency) {
		this.wordfrequency = wordfrequency;
	}


	public Map<String, Integer> getwordfrequency() {
		return wordfrequency;
	}

	public void setwordfrequency(Map<String, Integer> wordfrequency) {
		this.wordfrequency = wordfrequency;
	}

}