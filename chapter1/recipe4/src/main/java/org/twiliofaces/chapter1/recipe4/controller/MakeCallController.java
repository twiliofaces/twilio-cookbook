package org.twiliofaces.chapter1.recipe4.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.chapter1.recipe4.controller.util.Utils;

import com.twilio.sdk.TwilioRestException;

@Named
@SessionScoped
public class MakeCallController implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String toNumber = "12345678"; // Number to call
   private String called;
   static String ENDPOINT = "/callback.twiml";
   Logger logger = Logger.getLogger(MakeCallController.class.getName());

   @Inject
   Caller caller;

   public MakeCallController()
   {
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

   public String call()
   {
      if (called != null && !called.isEmpty())
      {
         try
         {
            String sid = caller.to(called).endpoint(ENDPOINT + "?+number=" + called).call();
            logger.info("call sid:" + sid);
            Utils.addFacesMessage("Ok..", "Connecting... " + sid);
            return null;
         }
         catch (TwilioRestException e)
         {
            e.printStackTrace();
            Utils.addFacesMessage("Error!!", e.getMessage());
            return null;
         }

      }
      else
      {
         Utils.addFacesMessage("Attention", "Must specify your phone number");
         return null;
      }
   }
}
