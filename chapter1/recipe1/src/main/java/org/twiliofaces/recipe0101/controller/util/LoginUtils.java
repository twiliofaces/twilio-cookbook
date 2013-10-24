/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipe0101.controller.util;

public class LoginUtils {

	
	public static String generate_token(){
		
	}
	
	public static String cleanVar(String key){
		String retVal = "";
		retVal = (key != null && !key.isEmpty()) ? key : "";
		switch(key){
			case "username":
			case "password":
				return retVal.replaceAll("/[^A-Za-z0-9]/", "");
			case "phone_number":
				return retVal.replaceAll("/[^0-9]/", "");
			case "message":
				return retVal.replaceAll("/[^A-Za-z0-9 ,]/", "");
			default:
				break;
		}
		return retVal;
	}

	public static String  user_generate_token(String username, String phoneNum, String method="calls"){
	    global accountsid, authtoken, fromNumber;
	    password = substr(md5(time().rand(0, 10^10)), 0, 10);
	    _SESSION["username"] = username;
	    _SESSION["password"] = password;
	    client = new Services_Twilio(accountsid, authtoken);
	    content = "Your newly generated password is ".password." To repeat that, your password is ".password;
	    item = client->account->method->create(
	                fromNumber,    // The Twilio number we"re sending from
	                phoneNum,      // The user"s phone number
	                content
	            );
	    message = "A new password has been generated and sent to your phone number.";
	    return message;
	}
	public static String  user_login(String username, String submitted) {
		String stored = _SESSION["password"];
		String message;
	    if (stored == submitted) {
	        message = "Hello and welcome back username";
	    } else {
	        message = "Sorry, that's an invalid username and password combination.";
	    }
	    unset(_SESSION["username"]);
	    unset(_SESSION["password"]);
	    return message;
	}
}
