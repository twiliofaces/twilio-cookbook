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

   static String ENDPOINT = "";
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
