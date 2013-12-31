/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.utils;

import org.twiliofaces.cdi.extension.util.Account;
import org.twiliofaces.recipes.model.UserAuth;

public class AccountUtils
{
   public static Account convert(UserAuth userAuth)
   {
      if (userAuth != null)
      {
         Account account = new Account();
         account.setApplicationSid(userAuth.getApplicationSid());
         account.setTwilioNumber(userAuth.getTwilioNumber());
         account.setTwilioSid(userAuth.getTwilioSid());
         account.setTwilioToken(userAuth.getTwilioToken());
         return account;
      }
      return null;
   }
}
