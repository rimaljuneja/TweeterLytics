package controllers;

import play.mvc.*;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public Result index() {
		
		Twitter twitter = TwitterFactory.getSingleton();
		
		Query query = new Query("Dhoni");
		
		QueryResult result;
		
		try {
			
			result = twitter.search(query);
			
			for (Status status : result.getTweets()) {
				
				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok(views.html.index.render());
	}

}
