/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.controller.util;


public class LoginUtils {

	public static String cleanString(String key) {
		String retVal = (key != null && !key.isEmpty()) ? key : "";

		return retVal.replaceAll("/[^A-Za-z0-9]/", "");

	}

	public static String cleanNumvber(String key) {
		String retVal = (key != null && !key.isEmpty()) ? key : "";

		return retVal.replaceAll("/[^0-9]/", "");

	}

}
