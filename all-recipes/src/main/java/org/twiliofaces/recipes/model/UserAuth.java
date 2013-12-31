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
import javax.persistence.Transient;

@Entity
public class UserAuth implements Serializable
{

   private static final long serialVersionUID = 1L;

   private Long id;
   private String username;
   private String name;
   private String password;
   private String role;

   private String oldPassword;
   private String newPassword;
   private String confirmPassword;

   private boolean admin;
   private boolean random;
   private String applicationSid;
   private String twilioNumber;
   private String twilioSid;
   private String twilioToken;

   public UserAuth()
   {
      // TODO Auto-generated constructor stub
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

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   @Transient
   public String getOldPassword()
   {
      return oldPassword;
   }

   public void setOldPassword(String oldPassword)
   {
      this.oldPassword = oldPassword;
   }

   @Transient
   public String getNewPassword()
   {
      return newPassword;
   }

   public void setNewPassword(String newPassword)
   {
      this.newPassword = newPassword;
   }

   @Transient
   public String getConfirmPassword()
   {
      return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword)
   {
      this.confirmPassword = confirmPassword;
   }

   @Transient
   public boolean isAdmin()
   {
      return admin;
   }

   public void setAdmin(boolean admin)
   {
      this.admin = admin;
   }

   @Transient
   public void verifyIfAdmin()
   {
      if (role.equals("admin"))
      {
         setAdmin(true);
      }
      else
      {
         setAdmin(false);

      }
   }

   @Transient
   public boolean isRandom()
   {
      return random;
   }

   public void setRandom(boolean random)
   {
      this.random = random;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
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
