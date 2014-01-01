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

import org.twiliofaces.chapter1.recipe1.service.AsyncCallService;
import org.twiliofaces.chapter1.recipe1.service.NumbersRepository;
import org.twiliofaces.recipes.utils.MessageUtils;
import org.twiliofaces.recipes.utils.StringUtils;

@Named
@SessionScoped
public class TwoFactorVoiceController implements Serializable
{

   private static final long serialVersionUID = 1L;

   Logger logger = Logger.getLogger(TwoFactorVoiceController.class.getName());

   @Inject
   NumbersRepository numbersRepository;

   @Inject
   AsyncCallService asyncCallService;

   private String username;
   private String number;
   private String verifyPassword;

   public TwoFactorVoiceController()
   {
   }

   public void generatePassword()
   {
      if (username != null && !username.isEmpty() && number != null
               && !number.isEmpty())
      {
         username = StringUtils.cleanString(username);
         number = StringUtils.cleanNumber(number);
         String password = "" + (new Random()).nextInt(900000000)
                  + 1000000000;
         numbersRepository.putUsernameNumberPassword(username, number,
                  password);
         logger.info("new username-number-password created: " + username
                  + " " + number + " " + password);
         asyncCallService.sendPassword(number);
         MessageUtils.addFacesMessage("a new password is generated",
                  "you will receive a call with your temporany password");
      }
      else
      {
         MessageUtils.addFacesMessage("some parameter is null or empty",
                  "please retry");
      }
   }

   public void verifyPassword()
   {
      if (username != null && !username.isEmpty() && verifyPassword != null
               && !verifyPassword.isEmpty())
      {
         if (numbersRepository.containsUsername(username))
         {
            String password = numbersRepository.getByUsername(username);
            if (password.equals(verifyPassword))
               MessageUtils.addFacesMessage("verification of passsword",
                        "the password is correct");
            else
               MessageUtils.addFacesMessage("verification of passsword",
                        "the password is not correct");
         }

      }
      else
      {
         MessageUtils.addFacesMessage("verification of passsword",
                  "some parameter is null or empty");

      }
   }

   public void reset()
   {
      username = null;
      number = null;
   }

   public void restart()
   {
      numbersRepository.removeNumberPassword(number);
      username = null;
      number = null;
      verifyPassword = null;
   }
}
