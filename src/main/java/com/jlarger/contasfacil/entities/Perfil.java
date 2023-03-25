package com.jlarger.contasfacil.entities;

import com.jlarger.contasfacil.entities.type.TpPerfil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TpPerfil tpPerfil;

	public Perfil() {

	}

	public Perfil(TpPerfil tpPerfil) {
		this.tpPerfil = tpPerfil;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TpPerfil getTpPerfil() {
		return tpPerfil;
	}

	public void setTpPerfil(TpPerfil tpPerfil) {
		this.tpPerfil = tpPerfil;
	}
	
}