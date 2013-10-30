package org.twiliofaces.chapter1.recipe6.util;

public class ExtensionUtils
{
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
