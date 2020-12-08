package services;

import java.util.List;
import java.util.concurrent.CompletionStage;

import models.Tweet;

/**
 * Twitter API Interface containg declaration of API methods
 * @author Azim Surani
 * @version 1.0.0
 */
public interface TwitterApi {
	
	public abstract CompletionStage<List<Tweet>> searchForKeywordAndGetTweets(final String keyword);
	
	public abstract CompletionStage<List<Tweet>> getUserTimelineByID(final String username);
	
	
}
