package org.twiliofaces.chapter1.recipe1.service;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Caller;

import com.twilio.sdk.TwilioRestException;

@Stateless
@LocalBean
public class AsyncCallService {

	static String ENDPOINT = "";
	@Inject
	Caller caller;
	
	Logger logger = Logger.getLogger(AsyncCallService.class.getName());

	@Asynchronous
	public void sendPassword(String number) {
		try {
			String sid = caller.to(number).endpoint(ENDPOINT).call();
			logger.info("call sid:" + sid);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
