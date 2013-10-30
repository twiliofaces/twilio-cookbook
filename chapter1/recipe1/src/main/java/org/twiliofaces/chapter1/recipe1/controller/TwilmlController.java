package org.twiliofaces.chapter1.recipe1.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.notification.To;

@RequestScoped
@Named
public class TwilmlController
{

   @Inject
   NumbersController numbersController;

   @Inject
   @To
   String to;

   public TwilmlController()
   {
   }

   public String getPasswordMessage()
   {
      if (to != null && !to.isEmpty() && numbersController.containsNumber(to))
         return "Your newly generated password is "
                  + numbersController.getByNumber(to)
                  + " To repeat that, your password is "
                  + numbersController.getByNumber(to);
      else
         return "ops.. i don't know your number";
   }

}
