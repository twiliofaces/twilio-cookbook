package org.twiliofaces.chapter1.recipe6.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.chapter1.recipe6.model.CompanyNumber;
import org.twiliofaces.inject.notification.Digits;

@Named
@RequestScoped
public class CompanyDirectoryController
{

   String receptionistPhoneNumber = ""; // CONFIGURATION receptionist_phone_number
   @Inject
   @Digits
   String digits;

   @Inject
   CompanyDirectoryMapController companyDirectoryMapController;

   public CompanyDirectoryController()
   {
   }

   public String getDigits()
   {
      return digits;
   }

   public void setDigits(String digits)
   {
      this.digits = digits;
   }

   public String getPhoneNumberByExtension()
   {
      CompanyNumber companyNumber = companyDirectoryMapController.getPhoneNumberByExtension(getDigits());
      if (companyNumber != null && companyNumber.getPhone() != null && !companyNumber.getPhone().isEmpty())
         return companyNumber.getPhone();
      return null;
   }

   public String getReceptionistPhoneNumber()
   {
      return receptionistPhoneNumber;
   }

   public void setReceptionistPhoneNumber(String receptionistPhoneNumber)
   {
      this.receptionistPhoneNumber = receptionistPhoneNumber;
   }

}
