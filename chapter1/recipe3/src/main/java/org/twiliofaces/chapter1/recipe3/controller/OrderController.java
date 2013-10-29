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

import org.twiliofaces.inject.notification.AccountSid;
import org.twiliofaces.inject.notification.Body;
import org.twiliofaces.inject.notification.From;
import org.twiliofaces.inject.notification.MessageSid;
import org.twiliofaces.inject.notification.To;

@Named
@RequestScoped
public class OrderController
{

   Logger logger = Logger.getLogger(OrderController.class.getName());

   private Map<Long, String> orders;

   @Inject
   @Body
   String body;

   @Inject
   @MessageSid
   String messageSid;

   @Inject
   @AccountSid
   String accountSid;

   @Inject
   @From
   String from;

   @Inject
   @To
   String to;

   public String reply()
   {
      if (body != null && !body.isEmpty())
      {
         if (getOrders().containsKey(body.trim()))
         {
            return "Your order is currently set at status '" + getOrders().get(body.trim()) + "'";
         }
         else
         {
            return "No Order Matching that ID was found";
         }
      }
      else
      {
         return "Please send us your order id and we will look it up ASAP";
      }
   }

   public void evaluate()
   {
      logger.info("messageSid: " + messageSid);
      logger.info("accountSid: " + accountSid);
      logger.info("body: " + body);
      logger.info("from: " + from);
      logger.info("to: " + to);
   }

   public Map<Long, String> getOrders()
   {
      if (orders == null)
      {
         orders = new HashMap<Long, String>();
         orders.put(111L, "shipped");
         orders.put(222L, "processing");
         orders.put(333L, "awaiting fullfillment");
      }
      return orders;
   }

   public void setOrders(Map<Long, String> orders)
   {
      this.orders = orders;
   }

}
