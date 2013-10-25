/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.controller;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.cdi.doers.Caller;

import com.twilio.sdk.TwilioRestException;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Caller caller;

	private static String METHOD = "CALLS";

	private String username;
	private String password;
	private String phoneNumber;
	private String message;

	public String generatePassword() {
		if (this.username != null && !this.username.isEmpty()
				&& this.phoneNumber != null && !this.phoneNumber.isEmpty()) {
			this.message = generateToken();

		} else {

		}
		return "";
	}

	public String verify() {
		if (this.username != null && !this.username.isEmpty()
				&& this.password != null && !this.password.isEmpty()) {
		}
		return "";
	}

	public String reset() {

		return "";
	}

	public String generateToken() {

		String password = "" + (new Random()).nextInt(900000000) + 1000000000;

		String content = "Your newly generated password is " + password
				+ " To repeat that, your password is " + password;
		try {
			String sid = caller.to(phoneNumber).endpoint("").call();
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "A new password has been generated and sent to your phone number.";
		return message;
	}

	public String user_login(String username, String submitted) {
		String message;
		if (password.equals(submitted)) {
			message = "Hello and welcome back username";
		} else {
			message = "Sorry, that's an invalid username and password combination.";
		}

		return message;
	}
}
