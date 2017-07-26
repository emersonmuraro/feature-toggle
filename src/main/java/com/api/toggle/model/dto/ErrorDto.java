package com.api.toggle.model.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2465458199120029838L;


	private String mensagem;

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


}
