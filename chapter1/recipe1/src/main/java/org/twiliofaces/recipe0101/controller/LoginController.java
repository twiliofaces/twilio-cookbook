package org.twiliofaces.recipe0101.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private String username;
	private String password;
	private String phoneNumber;
	private String message;

	public String generatePassword(){
			if( this.username != null && !this.username.isEmpty() && 
					this.phoneNumber != null && !this.phoneNumber.isEmpty()) {
				this.message = user_generate_token(this.username, this.phoneNumber);
				
			} else {
				
			}
			return "";
		}
	}

	public String verify() {
		if( this.username != null && !this.username.isEmpty() && 
				this.password != null && !this.password.isEmpty()) {
		}
		return "";
	}
	
	public String reset(){
		
		return "";

}
