/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.chapter1.recipe3.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.notification.From;

@Named
@RequestScoped
public class OrderController {

	Logger logger = Logger.getLogger(OrderController.class.getName());

	private Map<Long, String> orders;
	
	@Inject
	@From
	String from;
	


	public void reply() {

	}

	public Map<Long, String> getOrders() {
		if (orders == null) {
			orders = new HashMap<Long, String>();
			orders.put(111L, "shipped");
			orders.put(222L, "processing");
			orders.put(333L, "awaiting fullfillment");
		}
		return orders;
	}

	public void setOrders(Map<Long, String> orders) {
		this.orders = orders;
	}

}
