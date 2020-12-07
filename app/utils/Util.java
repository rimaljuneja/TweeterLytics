package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

/**
 * Contains utility methods which is common for entire application
 *
 * @author HGG02
 * @version 1.0.0
 */
public class Util {
	
	/**
	 * Generates JSON Response from this specified object
	 * @param response Response Object
	 * @param success Success
	 * @return JSON Object Response
	 * @author HGG02
	 */
	public static ObjectNode createResponse(Object response, boolean success) {
		
        ObjectNode result = Json.newObject();
        
        result.put("isSuccessful", success);
        
        result.putPOJO("data", response);
        	
        return result;
    }
}
