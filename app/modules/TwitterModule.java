package modules;
import com.google.inject.AbstractModule;
import services.TwitterApi; 
import services.TwitterImpl;

/**
 * Class to bind main Implementation to TwitterApi
 * @author Azim Surani
 * @version 1.0.0
 */
public class TwitterModule extends AbstractModule {
	
	@Override
	protected final void configure() {
		bind(TwitterApi.class).to(TwitterImpl.class);
	}
}