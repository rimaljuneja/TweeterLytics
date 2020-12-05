package controllers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.Mockito;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import actors.TimeActor;
import actors.UserSearchActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import models.Tweet;

import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.Helpers;
import play.test.WithApplication;
import services.ProfileService;
import services.TweetService;
import services.TwitterApi;
import services.TwitterTest;


import static play.inject.Bindings.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.stream.Stream;


public class HomeControllerTest extends WithApplication {
	
	Map<String,String> mockSentiment;
	
	Map<String,Integer> mockstats;
	
	TweetService tweetService;
	
	ProfileService profileTimelineService;
	
	HomeController homeProfileController;
	
	Map<String,List<Tweet>> timelineMockTweets;
	
	ActorSystem system;
	
	private Map<String,Integer> wordMap = new HashMap<>();
	
	TwitterApi testApi;
	
	private static Application testApp;
	
	@BeforeClass
	public static void initTestApp() {
		testApp = new GuiceApplicationBuilder()
				.overrides(bind(TwitterApi.class).to(TwitterTest.class))
				.build();
	}
	
	@AfterClass
	public static void stopTestApp() {
		Helpers.stop(testApp);
	}

	
	@Before
	public void initAll() {
	
		testApi = testApp.injector().instanceOf(TwitterApi.class);

		profileTimelineService = Mockito.mock(ProfileService.class);
		
		tweetService = new TweetService();

		system = ActorSystem.create();
		
		Path path = Paths.get("Wordlist/positive-words.txt");

		try (Stream<String> input = Files.lines(path))  {

			input.parallel().forEach(word -> wordMap.put(word, 1));

		} catch (IOException e) {

			e.printStackTrace();

		}

		path = Paths.get("Wordlist/negative-words.txt");

		try (Stream<String> input = Files.lines(path))  {

			input.parallel().forEach(word -> wordMap.put(word, -1));

		} catch (IOException e) {

			e.printStackTrace();

		}

		mockSentiment = new HashMap<>();
		// Happy Tweets for keyword I am Happy
		mockSentiment.put("I am Happy", "happy");

		// Sad Tweets for keyword I am feeling Lonely
		mockSentiment.put("I am feeling Lonely", "sad");

		// Neutral Tweets for keyword Canada Economy
		mockSentiment.put("Canada Economy", "neutral");


		timelineMockTweets = new HashMap<>();

		timelineMockTweets.put("Rimal",new ArrayList<> (Arrays.asList
				(new Tweet("I am happy that audience is appreciating good content on OTT and are looking beyond "
						+ "?hot? scenes ? @jaysoni25Follow Us on @iwmbuzz @krishnavbha tt@TheRealPriya#JaySoni "
						+ "#KrishnaBhatt #mustread #PriyaBanerjee #Twisted3https://t.co/l6J10HjRJ4","iwmbuzz"),
						new Tweet("@James33172570 Hey sister James, it doesn't matter, I am happy for you got a shiny ??????","shinybaobao"),
						new Tweet("a follow has been sent to you, eonni ? anyway i am a flover and i am happy when you're comeback hehehe https://t.co/fuY30ph0f4",
								"kwcnjian"),
						new Tweet("I am happy!#affirmations #leadership #leadershipdevelopment #management #managementdevelopment #leadershipcoaching "
								+ "#managementcoaching #balance dlife #worklifebalance #lifecoaching https://t.co/6rBtlLa5T4","CoachingforInsp"), 

						new Tweet("Thogh I don't watch his channel because of too much level of noise pollution over there yet I am happy to see the"
								+ " SC granting him the bail.#IDo ntSupportRepublic","GaneshJsmith"),
						new Tweet("On this Veterans Day 2020, I am happy to announce the availability of my new book!Conducting performance-based"
								+ " Instructional Analysishttps://t. co/NvgcqUBZbk https://t.co/YDoA4vkNtl","guywwallace"), 
						new Tweet("@OfficialMonstaX .My life we ??love you I want to give you happiness every day, you are the most beautiful person"
								+ " in my heart and I am happy wh en you are happy #LoveKilla2ndWin #MONSTAX@OfficialMonstaX","Andyymonbebe"),
						new Tweet("@nessahatake21 I AM SO GLAD YOU LIKE HIM SJDJSJ I am happy when other people love Renji too!! ????????? I?ve been a "
								+ "Renji fan (and simp) for ye ars already ????","AliceLaiho"), 
						new Tweet("The only person I compete against is my past self. Have I managed better than yesterday. If the answer is yes I am happy."
								,"RekaSundaram"),
						new Tweet("Democrats couldn't figure out how on earth they could unseat Donald Trump, so they settle for British product (Rigging)"
								+ " sold to Nigeria. But wi th this one rubbish act their journey to White House has been made more difficult. Sorry!!"
								+ "I am happy for their are still good people.","theophiletra"),
						new Tweet("@yumekosh ok am i supposed to be hurt or sum,,, i am happy for you??? ?","cherrykawa"),
						new Tweet("@DoMe3908 I am happy you feel that way???","jousuke_SandW"),
						new Tweet("I am happy. You can?t spoil my mood. I am happy. I?m about be fed as a stunt brand shipper that I am. "
								+ "I am happy. You can?t spoil my mood. http s://t.co/pKcdt3wKgu","Nezr1"), 
						new Tweet("@SilenceInPolish Wspania?y Genera?, strateg, Cz?owiek.I am happy to learn that another monument will be "
								+ "erected in March in Breda. Wonderful D utchmen !","halina_moulin"),
						new Tweet("@Domidest @norwegian76 @seanhannity I never thought I would say this, but right now I am happy that I live"
								+ " in South Africa and not the US!","Wi ldFigg"),
						new Tweet("I am blessedI cannot be disadvantagedI am happyI am lovedI am thankfulI am grateful","Oluwatosinoj"),
						new Tweet("@weekend3warrior Wow. Not sure if I am happy or fearful to see that?","mja2231"))));

		timelineMockTweets.put("Azim",new ArrayList<> (Arrays.asList
				(new Tweet("I am happy that audience is appreciating good content on OTT and are looking beyond "
						+ "?hot? scenes ? @jaysoni25Follow Us on @iwmbuzz @krishnavbha tt@TheRealPriya#JaySoni "
						+ "#KrishnaBhatt #mustread #PriyaBanerjee #Twisted3https://t.co/l6J10HjRJ4","iwmbuzz"),
						new Tweet("@James33172570 Hey sister James, it doesn't matter, I am happy for you got a shiny ??????","shinybaobao"),
						new Tweet("a follow has been sent to you, eonni ? anyway i am a flover and i am happy when you're comeback hehehe https://t.co/fuY30ph0f4",
								"kwcnjian"),
						new Tweet("I am happy!#affirmations #leadership #leadershipdevelopment #management #managementdevelopment #leadershipcoaching "
								+ "#managementcoaching #balance dlife #worklifebalance #lifecoaching https://t.co/6rBtlLa5T4","CoachingforInsp"), 

						new Tweet("Thogh I don't watch his channel because of too much level of noise pollution over there yet I am happy to see the"
								+ " SC granting him the bail.#IDo ntSupportRepublic","GaneshJsmith"),
						new Tweet("On this Veterans Day 2020, I am happy to announce the availability of my new book!Conducting performance-based"
								+ " Instructional Analysishttps://t. co/NvgcqUBZbk https://t.co/YDoA4vkNtl","guywwallace"), 
						new Tweet("@OfficialMonstaX .My life we ??love you I want to give you happiness every day, you are the most beautiful person"
								+ " in my heart and I am happy wh en you are happy #LoveKilla2ndWin #MONSTAX@OfficialMonstaX","Andyymonbebe"),
						new Tweet("@nessahatake21 I AM SO GLAD YOU LIKE HIM SJDJSJ I am happy when other people love Renji too!! ????????? I?ve been a "
								+ "Renji fan (and simp) for ye ars already ????","AliceLaiho")))); 


	}

    @Test
    public void testMainSearch() {
    	
    	final TestKit testProbe = new TestKit(system);
    	
    	final ActorRef timeActor = system.actorOf(TimeActor.props());
    	
    	final ActorRef userSearchActor = system.actorOf(UserSearchActor.props(testProbe.getRef(),testApi,tweetService, wordMap));
    	
    	for(int i=0;i<2;i++) {
    		
    		for(Entry<String, String> tweets : mockSentiment.entrySet()) {

    	    	final ObjectMapper mapper = new ObjectMapper();
    	        
    	    	final ObjectNode request = mapper.createObjectNode();
    	        
    	    	request.set("keyword", mapper.convertValue(tweets.getKey(), JsonNode.class));

    	    	userSearchActor.tell(request, ActorRef.noSender());
    	    	
    	    	testProbe.expectMsgClass(ObjectNode.class);

    	    	
    		}
    		
    	}
    	
    	try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    	
		/*
		 * for(int i=0;i<2;i++) {
		 * 
		 * 
		 * for(Map.Entry<String, List<Tweet>> tweets : mockTweets.entrySet()) {
		 * 
		 * 
		 * when(tweetService.searchForKeywordAndGetTweets(tweets.getKey())).thenReturn(
		 * CompletableFuture.supplyAsync(()-> tweets.getValue()));
		 * 
		 * when(tweetService.getSentimentForTweets(tweets.getValue(),tweets.getKey(),
		 * homeController.getWordMap())).thenCallRealMethod();
		 * 
		 * CompletableFuture<Result> result = (CompletableFuture<Result>)
		 * homeController.getTweetsBySearch(tweets.getKey()); try { Result r =
		 * result.get(); assertEquals(OK, r.status()); } catch (InterruptedException e)
		 * {
		 * 
		 * e.printStackTrace(); } catch (ExecutionException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * }
		 */
    }
    
    @Test
    /*public void testUserTimeline() {

    	
    		for(Map.Entry<String, List<Tweet>> tweets : timelineMockTweets.entrySet()) {

    					
    			when(profileTimelineService.getUserTimelineByID(tweets.getKey())).thenReturn(CompletableFuture.supplyAsync(()-> tweets.getValue()));
    			//when(profileTimelineService.getUserTimelineByID(tweets.getKey())).thenCallRealMethod();
 
    			CompletableFuture<Result> result = (CompletableFuture<Result>) homeController.getUserTimeline(tweets.getKey());
    			try {
    				Result r = result.get();
    				assertEquals(OK, r.status());
    			} catch (InterruptedException e) {

    				e.printStackTrace();
    			} catch (ExecutionException e) {

    				e.printStackTrace();
    			}

    		}
    		
    	}*/
    
    /*
    @Test
    public void testWordStatistics() {
    	for(int i=0;i<2;i++) {
        	
        	
    		for(Map.Entry<String, List<Tweet>> tweets : mockTweets.entrySet()) {


    			when(tweetService.searchForKeywordAndGetTweets(tweets.getKey())).thenReturn(CompletableFuture.supplyAsync(()-> tweets.getValue()));		

    			when(tweetService.getWordLevelStatistics(tweets.getValue())).thenCallRealMethod();

    			CompletableFuture<Result> result = (CompletableFuture<Result>) homeController.getStatisticsForSearchTerm(tweets.getKey());
    			try {
    				Result r = result.get();
    				assertEquals(OK, r.status());
    			} catch (InterruptedException e) {

    				e.printStackTrace();
    			} catch (ExecutionException e) {

    				e.printStackTrace();
    			}

    		}
    		
    	}
    }
    
    
    
    @Test
    public void testSearchByHashtag() {
    	for(int i=0;i<2;i++) {
        	
        	
    		for(Map.Entry<String, List<Tweet>> tweets : mockTweets.entrySet()) {


    			when(tweetService.searchForKeywordAndGetTweets(tweets.getKey())).thenReturn(CompletableFuture.supplyAsync(()-> tweets.getValue()));		

    			when(tweetService.getHashtagForTweets(tweets.getValue(),tweets.getKey())).thenCallRealMethod();

    			CompletableFuture<Result> result = (CompletableFuture<Result>) homeController.getTweetByHashtag(tweets.getKey());
    			try {
    				Result r = result.get();
    				assertEquals(OK, r.status());
    			} catch (InterruptedException e) {

    				e.printStackTrace();
    			} catch (ExecutionException e) {

    				e.printStackTrace();
    			}

    		}
    		
    	}
    }*/
    
	@After
	public void afterAll() {
		TestKit.shutdownActorSystem(system);
		system = null;
	}

}
