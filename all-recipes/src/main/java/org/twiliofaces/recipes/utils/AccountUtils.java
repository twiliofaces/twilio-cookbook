/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.utils;

import org.twiliofaces.cdi.extension.util.Account;
import org.twiliofaces.recipes.model.UserAccount;

public class AccountUtils
{
   public static UserAccount convert(Account account)
   {
      if (account != null)
         return new UserAccount(account);
      return null;
   }

}
