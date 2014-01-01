/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe2.controller;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.chapter1.recipe2.service.AsyncSendService;
import org.twiliofaces.recipes.utils.MessageUtils;
import org.twiliofaces.recipes.utils.StringUtils;

@Named
@SessionScoped
public class TwoFactorSmsController implements Serializable
{

   private static final long serialVersionUID = 1L;

   Logger logger = Logger.getLogger(TwoFactorSmsController.class.getName());

   @Inject
   AsyncSendService asyncCallService;

   private String username;
   private String password;
   private String number;
   private String verifyPassword;

   public TwoFactorSmsController()
   {
   }

   public void generatePassword()
   {
      if (username != null && !username.isEmpty() && number != null
               && !number.isEmpty())
      {
         username = StringUtils.cleanString(username);
         number = StringUtils.cleanNumber(number);
         password = "" + (new Random()).nextInt(900000000) + 1000000000;
         logger.info("new username-number-password created: " + username
                  + " " + number + " " + password);
         asyncCallService.sendPassword(number,
                  "Your newly generated password is " + password);
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
         if (password.equals(verifyPassword))
            MessageUtils.addFacesMessage("verification of passsword",
                     "the password is correct");
         else
            MessageUtils.addFacesMessage("verification of passsword",
                     "the password is not correct");

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
      username = null;
      number = null;
      password = null;
      verifyPassword = null;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getNumber()
   {
      return number;
   }

   public void setNumber(String number)
   {
      this.number = number;
   }

   public String getVerifyPassword()
   {
      return verifyPassword;
   }

   public void setVerifyPassword(String verifyPassword)
   {
      this.verifyPassword = verifyPassword;
   }
}
