/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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