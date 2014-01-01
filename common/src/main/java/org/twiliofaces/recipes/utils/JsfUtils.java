package org.twiliofaces.recipes.utils;

import javax.faces.context.FacesContext;

public class JsfUtils
{
   public static String getServerName()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
   }
}
