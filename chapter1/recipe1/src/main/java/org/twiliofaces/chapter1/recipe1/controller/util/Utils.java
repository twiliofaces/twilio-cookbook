/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe1.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Utils {

	public static String cleanString(String key) {
		String retVal = (key != null && !key.isEmpty()) ? key : "";

		return retVal.replaceAll("/[^A-Za-z0-9]/", "");

	}

	public static String cleanNumber(String key) {
		String retVal = (key != null && !key.isEmpty()) ? key : "";

		return retVal.replaceAll("/[^0-9]/", "");

	}

	public static void addFacesMessage(String summary, String message) {
		addFacesMessage(null, summary, message, "");
	}

	public static void addFacesMessage(Severity severity, String summary,
			String message, String forComponentId) {
		FacesMessage fm = new FacesMessage(message);
		fm.setSummary(summary);
		if (severity != null) {
			fm.setSeverity(severity);
		} else {
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage(forComponentId, fm);

	}

}
