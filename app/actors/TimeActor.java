package actors;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

/**
 * This actor class notifies every registered actor to push new data in fixed interval of time period.
 * @author HGG02
 * @version 1.0.0
 */
public class TimeActor extends AbstractActorWithTimers {
    
	//Keeps a set of ActorRefs (registered Actors)
	Set<ActorRef> userActors = new HashSet<>();
	
	/**
	 * This class protocol helps other actors to register themselves.
	 * 
	 * @author HGG02
	 * @version 1.0.0
	 */
	public static class RegisterMsg {
		
    }
	
	/**
	 * This class protocol helps other actors to de-register themselves.
	 * 
	 * @author HGG02
	 * @version 1.0.0
	 */
	public static class DeRegisterMsg {
		
    }
	
	/**
	 * This class protocol helps to notify register actors to push new data.
	 * 
	 * @author HGG02
	 * @version 1.0.0
	 */
    private static final class Tick {
    	
    }
    
    /**
	 * This class protocol helps registered actors to recognize that they need to push new data.
	 * 
	 * @author HGG02
	 * @version 1.0.0
	 */
    public static final class PushNewData {
     
    }
    
    /**
     * Factory method to create instance of Time Actor.
     * 
     * @return Props "Instance of Time Actor"
     * @author HGG02
     */
    public static Props props() {
		return Props.create(TimeActor.class,() -> new TimeActor());
	}

    /**
     * It sends Tick message every 30 seconds to itself.
     * 
     * @author HGG02
     */
    @SuppressWarnings("deprecation")
	@Override
    public void preStart() {
        getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(30, TimeUnit.SECONDS));
    }
    
    /**
     * It receives messages and decides action based on message type.
     * 
     * @author HGG02
     */
    @Override
	public Receive createReceive() {
    	
		return receiveBuilder()
				.match(RegisterMsg.class, msg-> userActors.add(sender()))
				.match(Tick.class, msg -> notifyRegisteredActors())
				.match(DeRegisterMsg.class, msg-> userActors.remove(sender()))
				.build();
	}
    
    /**
     * It notifies all registered actors to push new data.
     * 
     * @author HGG02
     */
    private void notifyRegisteredActors() {
    	
    	PushNewData pushNewData = new PushNewData();
    	
        userActors.forEach(ar -> ar.tell(pushNewData, self()));
        
    }

}
