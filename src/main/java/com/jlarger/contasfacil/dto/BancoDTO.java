package com.jlarger.contasfacil.dto;

import java.io.Serializable;

import com.jlarger.contasfacil.entities.Banco;

public class BancoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigoNacional;
	private String nmBanco;
	
	public BancoDTO() {
	}

	public BancoDTO(Long id, String codigoNacional, String nmBanco) {
		this.id = id;
		this.codigoNacional = codigoNacional;
		this.nmBanco = nmBanco;
	}
	
	public BancoDTO(Banco banco) {
		this.id = banco.getId();
		this.codigoNacional = banco.getCodigoNacional();
		this.nmBanco = banco.getNmBanco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoNacional() {
		return codigoNacional;
	}

	public void setCodigoNacional(String codigoNacional) {
		this.codigoNacional = codigoNacional;
	}

	public String getNmBanco() {
		return nmBanco;
	}

	public void setNmBanco(String nmBanco) {
		this.nmBanco = nmBanco;
	}
	
}