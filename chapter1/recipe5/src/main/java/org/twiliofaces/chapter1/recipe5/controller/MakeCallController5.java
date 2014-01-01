/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe5.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.cdi.doers.RecordingReporter;
import org.twiliofaces.recipes.service.MailService;
import org.twiliofaces.recipes.utils.MessageUtils;

import com.twilio.sdk.TwilioRestException;

@Named
@SessionScoped
public class MakeCallController5 implements Serializable
{
   @Inject
   MailService mailService;

   private static final long serialVersionUID = 1L;
   private String toNumber = "12345678"; // YOUR PHONE NUMBER TO CALL
   private String toEmail = "mail@mail.com"; // YOUR EMAIL TO EMAIL YOU THE RECORDING
   private String called;
   static String URL = "/callback.twiml";
   static String RECORDING_URL = "recording.jsf";
   Logger logger = Logger.getLogger(MakeCallController5.class.getName());

   @Inject
   Caller caller;

   @Inject
   RecordingReporter recordingReporter;

   public MakeCallController5()
   {
   }

   public String call()
   {
      if (called != null && !called.isEmpty())
      {
         try
         {
            String sid = caller.to(called).url(URL + "?+number=" + called
                     // + "&record=true"
                     ).param("record", "true").call();
            logger.info("call sid:" + sid);
            String subject = "New phone recording from " + called;
            String serverName = null;
            StringBuffer content = new StringBuffer("You have a new phone recording from ").append(called)
                     .append(":\n\n")
                     .append(serverName + RECORDING_URL + "?sid=" + sid);
            String to = "";
            String from = "";
            mailService.send(from, to, subject, content.toString());
            // (RECORDING_URL, sid, sid, Utils.getServerName());
            MessageUtils.addFacesMessage("Ok..", "Connecting... " + sid);
            return null;
         }
         catch (TwilioRestException e)
         {
            e.printStackTrace();
            MessageUtils.addFacesMessage("Error!!", e.getMessage());
            return null;
         }

      }
      else
      {
         MessageUtils.addFacesMessage("Attention", "Must specify your phone number");
         return null;
      }
   }

   public String getToNumber()
   {
      return toNumber;
   }

   public void setToNumber(String toNumber)
   {
      this.toNumber = toNumber;
   }

   public String getCalled()
   {
      return called;
   }

   public void setCalled(String called)
   {
      this.called = called;
   }

   public String getToEmail()
   {
      return toEmail;
   }

   public void setToEmail(String toEmail)
   {
      this.toEmail = toEmail;
   }
}
