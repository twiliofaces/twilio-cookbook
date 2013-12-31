/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.utils;

import org.twiliofaces.cdi.extension.util.Account;
import org.twiliofaces.recipes.model.User;

public class AccountUtils
{
   public static Account convert(User userAuth)
   {
      if (userAuth != null)
      {
         Account account = new Account();
         account.setTwilioNumber(userAuth.getTwilioNumber());
         account.setTwilioSid(userAuth.getTwilioSid());
         account.setTwilioToken(userAuth.getTwilioToken());
         return account;
      }
      return null;
   }
}
