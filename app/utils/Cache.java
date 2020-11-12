package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import models.Tweet;

public class Cache {
	
	private Map<String,List<Tweet>> cache;
	
	public Cache() { 
		cache = new HashMap<>();
	}
	
	public void put(String key,List<Tweet> tweets) {
		cache.putIfAbsent(key, tweets);
	}
	
	public List<Tweet> get(String key){
		return cache.getOrDefault(key,new ArrayList<>());
	}
	
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
