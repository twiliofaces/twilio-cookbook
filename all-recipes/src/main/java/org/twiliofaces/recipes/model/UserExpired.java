/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UserExpired")
public class UserExpired implements Serializable
{

   private static final long serialVersionUID = 1L;

   private Long id;
   private String username;
   private String name;
   private String mobile;
   private Date registrationDate;
   private Date expirationDate;

   public UserExpired()
   {
   }

   public UserExpired(User user)
   {
      this.username = user.getUsername();
      this.name = user.getName();
      this.mobile = user.getMobile();
      this.registrationDate = user.getRegistrationDate();
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getMobile()
   {
      return mobile;
   }

   public void setMobile(String mobile)
   {
      this.mobile = mobile;
   }

   @Temporal(TemporalType.TIMESTAMP)
   public Date getRegistrationDate()
   {
      return registrationDate;
   }

   public void setRegistrationDate(Date registrationDate)
   {
      this.registrationDate = registrationDate;
   }

   @Temporal(TemporalType.TIMESTAMP)
   public Date getExpirationDate()
   {
      return expirationDate;
   }

   public void setExpirationDate(Date expirationDate)
   {
      this.expirationDate = expirationDate;
   }

}
