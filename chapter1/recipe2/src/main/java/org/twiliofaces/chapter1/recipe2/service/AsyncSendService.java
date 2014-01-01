/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe2.service;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Sender;

import com.twilio.sdk.TwilioRestException;

@Stateless
@LocalBean
public class AsyncSendService
{

   @Inject
   Sender sender;

   Logger logger = Logger.getLogger(AsyncSendService.class.getName());

   @Asynchronous
   public void sendPassword(String number, String body)
   {
      try
      {
         String sid = sender.to(number).body(body).simpleSend();
         logger.info("msg id:" + sid);
      }
      catch (TwilioRestException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
