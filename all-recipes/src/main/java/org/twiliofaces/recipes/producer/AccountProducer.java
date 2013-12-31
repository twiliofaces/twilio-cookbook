/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.producer;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.cdi.extension.TwilioManager;
import org.twiliofaces.cdi.extension.util.Account;
import org.twiliofaces.cdi.producer.util.ELUtils;
import org.twiliofaces.inject.configuration.CustomTwilioAccount;
import org.twiliofaces.recipes.repository.UserRepository;
import org.twiliofaces.recipes.utils.AccountUtils;

@Named
@SessionScoped
public class AccountProducer implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Inject
   FacesContext facesContext;

   @Inject
   UserRepository userRepository;

   @Inject
   TwilioManager twilioManager;

   @CustomTwilioAccount
   @Produces
   public Account getAccount(InjectionPoint ip)
   {
      String accountName = ip.getAnnotated().getAnnotation(CustomTwilioAccount.class).accountName();
      if (accountName != null && accountName.trim().isEmpty() && twilioManager != null)
      {
         return twilioManager.getDefaultAccount();
      }
      else
      {
         if (ELUtils.isElExpression(accountName))
         {
            accountName = ELUtils.resolveElExpression(accountName, facesContext);
         }
         if (accountName != null)
            return AccountUtils.convert(userRepository.getAccountByName(accountName));
      }
      return null;
   }
}
