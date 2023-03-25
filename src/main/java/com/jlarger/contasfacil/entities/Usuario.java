package com.jlarger.contasfacil.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(	name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = "nmUsuario"), @UniqueConstraint(columnNames = "nmEmail") })
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false, length = 50)
	private String nmUsuario;
	
	@Column(unique=true, nullable=false, length = 255)
	private String nmEmail;
	
	@Column(unique=true, nullable=false, length = 255)
	private String nmSenha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "usuario_perfil", 
				joinColumns = @JoinColumn(name = "id_usuario"), 
				inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private Set<Perfil> perfis = new HashSet<Perfil>();
	
	public Usuario() {
	
	}

	public Usuario(Long id, String nmUsuario, String nmEmail, String nmSenha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.nmUsuario = nmUsuario;
		this.nmEmail = nmEmail;
		this.nmSenha = nmSenha;
		this.perfis = perfis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getNmEmail() {
		return nmEmail;
	}

	public void setNmEmail(String nmEmail) {
		this.nmEmail = nmEmail;
	}

	public String getNmSenha() {
		return nmSenha;
	}

	public void setNmSenha(String nmSenha) {
		this.nmSenha = nmSenha;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

}