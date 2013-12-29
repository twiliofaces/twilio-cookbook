/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.service;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Caller;

import com.twilio.sdk.TwilioRestException;

@Stateless
@LocalBean
public class AsyncCallService
{

   static final String URL = "";

   @Inject
   Caller caller;

   Logger logger = Logger.getLogger(AsyncCallService.class.getName());

   @Asynchronous
   public void sendPassword(String number)
   {
      try
      {
         String sid = caller.to(number).url(URL).call();
         logger.info("call sid:" + sid);
      }
      catch (TwilioRestException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
