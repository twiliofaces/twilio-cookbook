package org.twiliofaces.chapter1.recipe7.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.capability.TwilioCapabilityToken;

@RequestScoped
@Named
public class TwilioCapabilityTokenController
{

   @Inject
   @TwilioCapabilityToken
   String capabilityToken;

   public TwilioCapabilityTokenController()
   {
   }

   public String getCapabilityToken()
   {
      return capabilityToken;
   }

}