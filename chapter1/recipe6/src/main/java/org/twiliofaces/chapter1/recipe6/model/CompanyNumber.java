/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe6.model;

import java.io.Serializable;

public class CompanyNumber implements Serializable
{

   private static final long serialVersionUID = 1L;
   private String extension;
   private String phone;
   private String firstname;
   private String lastname;

   public CompanyNumber(String extension, String phone, String firstname, String lastname)
   {
      super();
      this.extension = extension;
      this.phone = phone;
      this.firstname = firstname;
      this.lastname = lastname;
   }

   public String getExtension()
   {
      return extension;
   }

   public void setExtension(String extension)
   {
      this.extension = extension;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }
}
