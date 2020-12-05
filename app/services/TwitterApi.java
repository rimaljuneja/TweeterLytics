package services;

import java.util.List;
import java.util.concurrent.CompletionStage;

import models.Tweet;

public interface TwitterApi {
	
	public abstract CompletionStage<List<Tweet>> searchForKeywordAndGetTweets(final String keyword);
	
}
