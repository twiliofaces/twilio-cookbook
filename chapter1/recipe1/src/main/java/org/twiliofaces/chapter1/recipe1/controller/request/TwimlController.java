/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.controller.request;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.chapter1.recipe1.service.NumbersRepository;
import org.twiliofaces.inject.notification.To;

@RequestScoped
@Named
public class TwimlController
{

   @Inject
   NumbersRepository numbersRepository;

   @Inject
   @To
   String to;

   public TwimlController()
   {
   }

   public String getPasswordMessage()
   {
      if (to != null && !to.isEmpty() && numbersRepository.containsNumber(to))
         return "Your newly generated password is "
                  + numbersRepository.getByNumber(to)
                  + " To repeat that, your password is "
                  + numbersRepository.getByNumber(to);
      else
         return "ops.. i don't know your number";
   }

}
