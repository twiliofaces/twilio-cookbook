/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.recipes.controller;

import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.twiliofaces.recipes.model.User;
import org.twiliofaces.recipes.repository.UserRepository;
import org.twiliofaces.recipes.utils.EmailUtils;
import org.twiliofaces.recipes.utils.PasswordUtils;

@Named
@SessionScoped
public class UserController implements Serializable
{

   private static final long serialVersionUID = 1L;
   boolean withHashAlgorithm = true;

   Logger logger = Logger.getLogger(getClass()
            .getCanonicalName());

   @Inject
   UserRepository userRepository;

   private User user;

   // -----------------------------------------------------

   public String save()
   {
      if (!checkUsername())
      {
         return null;
      }
      if (!checkPassword())
      {
         return null;
      }
      userRepository.persist(getUser());
      return "";
   }

   private boolean generateRandomPasswordAndSendSms(String title)
   {
      if (getUser().getMobile() == null || getUser().getMobile().isEmpty())
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Login name must be a valid email address",
                                    ""));
         return false;
      }
      String newPassword = UUID.randomUUID().toString().substring(1, 8);
      getUser().setNewPassword(newPassword);
      getUser().setConfirmPassword(newPassword);
      if (withHashAlgorithm)
      {
         getUser().setPassword(PasswordUtils.createPassword(newPassword));
      }
      else
      {
         getUser().setPassword(newPassword);
      }
      return sendSms(newPassword, title);
   }

   private boolean sendSms(String newPassword, String title)
   {
      // TODO Auto-generated method stub
      // TODO use user-provided twilio account information to send and sms to her mobile
      return true;
   }

   private boolean checkUsername()
   {
      if (getUser().getId() != null)
      {
         // username cannot be modified
         return true;
      }
      if (!getUser().isAdmin()
               && !EmailUtils.isValidEmailAddress(getUser().getUsername()))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Login name must be a valid email address",
                                    ""));
         return false;
      }
      if (userRepository.findByUsername(getUser().getUsername()) != null)
      {
         FacesContext.getCurrentInstance().addMessage("",
                  new FacesMessage("Login name already in use"));
         return false;
      }
      return true;
   }

   private boolean checkPassword()
   {
      if (getUser().isRandom())
      {
         boolean result = generateRandomPasswordAndSendSms("New account");
         if (!result)
         {
            FacesContext
                     .getCurrentInstance()
                     .addMessage(
                              "",
                              new FacesMessage("Failed to send password via SMS",
                                       ""));
         }
         return result;
      }
      if (getUser().getNewPassword() == null
               || getUser().getNewPassword().isEmpty())
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Password cannot be empty",
                                    ""));
         return false;
      }
      if (!getUser().getNewPassword().equals(getUser().getConfirmPassword()))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Password and password confirmation do not match",
                                    ""));
         return false;
      }
      if (withHashAlgorithm)
      {
         getUser().setPassword(
                  PasswordUtils.createPassword(getUser()
                           .getNewPassword()));
      }
      else
      {
         getUser().setPassword(getUser().getNewPassword());
      }
      return true;
   }

   public User getUser()
   {
      if (user == null)
      {
         this.user = new User();
         this.user.setRandom(true);
         this.user.setRole("user");

      }
      return user;
   }

   public void setUser(User element)
   {
      this.user = element;
   }

   public String renewPassword()
   {
      User user = userRepository.findByUsername(getUser().getUsername());
      if (user == null)
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Unknown email address",
                                    ""));
         return null;
      }
      if (!getUser().getMobile().equals(user.getMobile()))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Expected mobile number does not match the provided one",
                                    ""));
         return null;
      }
      if (!generateRandomPasswordAndSendSms("Password recovery"))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Failed to send password via SMS",
                                    ""));
         return null;
      }
      user.setPassword(getUser().getPassword());
      user.setNewPassword(getUser().getNewPassword());
      user.setConfirmPassword(getUser().getConfirmPassword());
      userRepository.update(user);
      setUser(user);
      return "/registration.xhtml";
   }
}