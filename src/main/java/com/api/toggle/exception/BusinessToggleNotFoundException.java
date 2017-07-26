package com.api.toggle.exception;

/**
 * Exception for toggle not found error 
 * @author emuraro
 *
 */
public class BusinessToggleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1952421029409991502L;
	private String message;
	
	public BusinessToggleNotFoundException(){
		super();
	}

	
	public BusinessToggleNotFoundException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

}
