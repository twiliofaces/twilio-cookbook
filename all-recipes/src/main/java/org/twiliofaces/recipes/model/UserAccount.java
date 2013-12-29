/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.twiliofaces.cdi.extension.util.Account;

@Entity
public class UserAccount implements Serializable
{
   private static final long serialVersionUID = 1L;

   private Long id;
   private String name;
   private String applicationSid;
   private String twilioNumber;
   private String twilioSid;
   private String twilioToken;

   public UserAccount()
   {
   }

   public UserAccount(Account account)
   {
      setName(account.getName());
      setApplicationSid(account.getApplicationSid());
      setTwilioNumber(account.getTwilioNumber());
      setTwilioSid(account.getTwilioSid());
      setTwilioToken(account.getTwilioToken());
   }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getApplicationSid()
   {
      return applicationSid;
   }

   public void setApplicationSid(String applicationSid)
   {
      this.applicationSid = applicationSid;
   }

   public String getTwilioNumber()
   {
      return twilioNumber;
   }

   public void setTwilioNumber(String twilioNumber)
   {
      this.twilioNumber = twilioNumber;
   }

   public String getTwilioSid()
   {
      return twilioSid;
   }

   public void setTwilioSid(String twilioSid)
   {
      this.twilioSid = twilioSid;
   }

   public String getTwilioToken()
   {
      return twilioToken;
   }

   public void setTwilioToken(String twilioToken)
   {
      this.twilioToken = twilioToken;
   }

}
