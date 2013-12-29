/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
