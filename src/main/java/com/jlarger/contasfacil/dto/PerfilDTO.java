package com.jlarger.contasfacil.dto;

import java.io.Serializable;

import com.jlarger.contasfacil.entities.Perfil;
import com.jlarger.contasfacil.entities.type.TpPerfil;

public class PerfilDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TpPerfil tpPerfil;
	
	public PerfilDTO() {
	}
	
	public PerfilDTO(Perfil perfil) {
		this.tpPerfil = perfil.getTpPerfil();
	}

	public PerfilDTO(TpPerfil tpPerfil) {
		super();
		this.tpPerfil = tpPerfil;
	}

	public TpPerfil getTpPerfil() {
		return tpPerfil;
	}

	public void setTpPerfil(TpPerfil tpPerfil) {
		this.tpPerfil = tpPerfil;
	}
	
}
