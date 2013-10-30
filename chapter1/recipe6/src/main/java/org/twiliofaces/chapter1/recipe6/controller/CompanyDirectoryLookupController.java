package org.twiliofaces.chapter1.recipe6.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CompanyDirectoryLookupController
{
   @Inject
   CompanyDirectoryMapController companyDirectoryMapController;

   public CompanyDirectoryLookupController()
   {
   }

}
