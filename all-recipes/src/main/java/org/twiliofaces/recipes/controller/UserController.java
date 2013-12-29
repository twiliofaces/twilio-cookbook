/*
 * Copyright 2013 GiavaCms.org.
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
import org.twiliofaces.recipes.model.UserAuth;
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

   private UserAuth element;

   // -----------------------------------------------------

   public String save()
   {
      if (userRepository.findByUsername(getElement().getUsername()) != null)
      {
         FacesContext.getCurrentInstance().addMessage("",
                  new FacesMessage("Nome utente non disponibile"));
         return null;
      }
      if (!EmailUtils.isValidEmailAddress(getElement().getUsername()))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Nome utente non valido",
                                    "Il nome utente deve essere costituito da un indirizzo email valido"));
         return null;
      }
      if (!controllPassword())
      {
         return null;
      }

      return "";
   }

   public String update()
   {
      if (!getElement().isAdmin()
               && !EmailUtils.isValidEmailAddress(getElement().getUsername()))
      {
         FacesContext
                  .getCurrentInstance()
                  .addMessage(
                           "",
                           new FacesMessage("Nome utente non valido",
                                    "Il nome utente deve essere costituito da un indirizzo email valido"));
         return null;
      }
      if (getElement().isRandom())
      {
         generateRandomPasswordAndSendEmail("Accesso al portale: modifica password");
      }
      else
      {
         if (getElement().getNewPassword() != null
                  && !getElement().getNewPassword().isEmpty())
         {
            if (withHashAlgorithm)
            {
               getElement().setPassword(
                        PasswordUtils.createPassword(getElement()
                                 .getNewPassword()));
            }
            else
            {
               getElement().setPassword(getElement().getNewPassword());
            }
         }
         else
         {
            logger.info("non e' stato richiesto un cambio password per account: "
                     + getElement().getUsername());
         }
      }
      return "";
   }

   private void generateRandomPasswordAndSendEmail(String title)
   {
      String newPassword = UUID.randomUUID().toString().substring(1, 8);
      if (withHashAlgorithm)
      {
         getElement().setPassword(PasswordUtils.createPassword(newPassword));
      }
      else
      {
         getElement().setPassword(newPassword);
      }
      String body = "La password dell'utente '" + getElement().getUsername()
               + "' è : " + newPassword;
      String result = "";
      logger.info(result);
   }

   private boolean controllPassword()
   {
      if (getElement().isRandom())
      {
         generateRandomPasswordAndSendEmail("Generazione nuovo account");
      }
      else
      {
         if (getElement().getNewPassword() == null
                  || getElement().getNewPassword().isEmpty())
         {
            FacesContext
                     .getCurrentInstance()
                     .addMessage(
                              "",
                              new FacesMessage("Password non inserita",
                                       "La password deve essere inserita obbligatoriamente!"));
            return false;
         }
         else
         {
            if (withHashAlgorithm)
            {
               getElement().setPassword(
                        PasswordUtils.createPassword(getElement()
                                 .getNewPassword()));
            }
            else
            {
               getElement().setPassword(getElement().getNewPassword());
            }
         }
      }
      return true;
   }

   public UserAuth getElement()
   {
      if (element == null)
         this.element = new UserAuth();
      return element;
   }

   public void setElement(UserAuth element)
   {
      this.element = element;
   }

}