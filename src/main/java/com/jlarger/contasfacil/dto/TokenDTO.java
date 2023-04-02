package com.jlarger.contasfacil.dto;

public class TokenDTO {
	
	private String accessToken;
	private Boolean isTokenValido;
	
	public TokenDTO() {
	}

	public TokenDTO(String accessToken, Boolean isTokenValido) {
		super();
		this.accessToken = accessToken;
		this.isTokenValido = isTokenValido;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Boolean getIsTokenValido() {
		return isTokenValido;
	}

	public void setIsTokenValido(Boolean isTokenValido) {
		this.isTokenValido = isTokenValido;
	}
	
}