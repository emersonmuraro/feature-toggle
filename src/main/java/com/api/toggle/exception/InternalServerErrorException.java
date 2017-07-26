package com.api.toggle.exception;

/**
 * Exception for internal server error 
 * @author emuraro
 *
 */
public class InternalServerErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -796457847126526198L;
	private String message;
	
	public InternalServerErrorException(){
		super();
	}

	
	public InternalServerErrorException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

}
