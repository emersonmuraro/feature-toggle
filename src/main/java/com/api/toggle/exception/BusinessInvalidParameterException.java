package com.api.toggle.exception;

/**
 * Exception for validation parameter error 
 * @author emuraro
 *
 */
public class BusinessInvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6533675819265163319L;
	private String message;
	
	public BusinessInvalidParameterException(){
		super();
	}

	
	public BusinessInvalidParameterException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

}
