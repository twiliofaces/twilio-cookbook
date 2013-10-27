/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.controller;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.chapter1.recipe1.controller.util.Utils;
import org.twiliofaces.chapter1.recipe1.service.AsyncCallService;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(LoginController.class.getName());

	@Inject
	NumbersController numbersController;

	@Inject
	AsyncCallService asyncCallService;

	private String username;
	private String number;
	private String verifyPassword;

	public void generatePassword() {
		if (username != null && !username.isEmpty() && number != null
				&& !number.isEmpty()) {
			username = Utils.cleanString(username);
			number = Utils.cleanNumber(number);
			String password = "" + (new Random()).nextInt(900000000)
					+ 1000000000;
			numbersController.putUsernameNumberPassword(username, number,
					password);
			logger.info("new username-number-password created: " + username
					+ " " + number + " " + password);
			asyncCallService.sendPassword(number);
			Utils.addFacesMessage("a new password is generated",
					"you will receive a call with your temporany password");
		} else {
			Utils.addFacesMessage("some parameter is null or empty",
					"please retry");
		}
	}

	public void verifyPassword() {
		if (username != null && !username.isEmpty() && verifyPassword != null
				&& !verifyPassword.isEmpty()) {
			if (numbersController.containsUsername(username)) {
				String password = numbersController.getByUsername(username);
				if (password.equals(verifyPassword))
					Utils.addFacesMessage("verification of passsword",
							"the password is correct");
				else
					Utils.addFacesMessage("verification of passsword",
							"the password is not correct");
			}

		} else {
			Utils.addFacesMessage("verification of passsword",
					"some parameter is null or empty");

		}
	}

	public void reset() {
		username = null;
		number = null;
	}

	public void restart() {
		numbersController.removeNumberPassword(number);
		username = null;
		number = null;
		verifyPassword = null;
	}
}
