package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

/**
 * Contains utility methods which will be common to whole application
 *
 * @author Everyone
 * @version 1.0.0
 */
public class Util {
	
	/**
	 * It helps to create JSON Response from sepcified object
	 * @param response 
	 * @param success
	 * @return ObjectNode 
	 * @author Everyone
	 */
	public static ObjectNode createResponse(Object response, boolean success) {
		
        ObjectNode result = Json.newObject();
        
        result.put("isSuccessful", success);
        
        if (response instanceof String) {
            result.put("data", (String) response);
        } else {
            result.putPOJO("data", response);
        }
        
        return result;
    }
}
