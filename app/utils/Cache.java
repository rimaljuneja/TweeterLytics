package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import models.Tweet;

/**
 * Custom cache implementation
 * @author HGG02
 * @version 1.0.0
 */
public class Cache {
	
	private Map<String,List<Tweet>> cache;
	
	public Cache() { 
		cache = new HashMap<>();
	}
	
	/**
	 * Set the list of tweets in cache
	 * @param key "Key to store tweet"
	 * @param tweets "List of tweets to store"
	 */
	public void put(String key,List<Tweet> tweets) {
		
		cache.putIfAbsent(key, tweets);
		
	}
	
	/**
	 * Fetch the tweets from cache if present else return empty list
	 * @param key "Key to fetch tweets"
	 * @return List of Tweets
	 * @author HGG02
	 */
	public List<Tweet> get(String key){
		
		return cache.getOrDefault(key,new ArrayList<>());
		
	}
	
	/**
	 * This method fetches the tweets from cache if present else gets tweets from Callable method passed to it.
	 * @param key "Key to fetch tweets"
	 * @param block "Block to execute if tweets are not found in cache"
	 * @return List of tweets
	 * @author HGG02
	 */
	public CompletionStage<List<Tweet>> getOrElseUpdate(String key,Callable<CompletionStage<List<Tweet>>> block){
		
		if(!this.get(key).isEmpty()) {
			
			return CompletableFuture.supplyAsync(()-> this.get(key));
			
		}else {
			
			try {
				
				return block.call().thenApplyAsync(tweets->{
					
					this.put(key, tweets);
					
					return tweets;
					
				});
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				return CompletableFuture.supplyAsync(()-> new ArrayList<>());
				
			}
		}
		
	}
}
