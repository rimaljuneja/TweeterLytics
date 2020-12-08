package services;

import java.util.List;
import java.util.concurrent.CompletionStage;

import models.Tweet;
import twitter4j.Status;

public interface TwitterApi {
	
	public abstract CompletionStage<List<Tweet>> searchForKeywordAndGetTweets(final String keyword);
	
	public abstract CompletionStage<List<Tweet>> getUserTimelineByID(final String username);
	
	
}
