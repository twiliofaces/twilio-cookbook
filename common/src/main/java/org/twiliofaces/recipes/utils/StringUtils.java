/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.utils;

public class StringUtils
{

   public static String cleanString(String key)
   {
      String retVal = (key != null && !key.isEmpty()) ? key : "";

      return retVal.replaceAll("/[^A-Za-z0-9]/", "");

   }

   public static String cleanNumber(String key)
   {
      String retVal = (key != null && !key.isEmpty()) ? key : "";

      return retVal.replaceAll("/[^0-9]/", "");

   }

   public static String stringToDigits(String str)
   {
      str = str.toLowerCase();
      String from = "abcdefghijklmnopqrstuvwxyz";
      String to = "22233344455566677778889999";
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < str.length(); i++)
      {
         char c = str.charAt(i);
         int index = from.indexOf(c);
         result.append(to.charAt(index));
      }
      return result.toString().replaceAll("/[^0-9]/", "");
   }

}
