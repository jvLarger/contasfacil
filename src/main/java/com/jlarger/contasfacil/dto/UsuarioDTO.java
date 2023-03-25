package com.jlarger.contasfacil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jlarger.contasfacil.entities.Perfil;
import com.jlarger.contasfacil.entities.Usuario;

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nmUsuario;
	private String nmEmail;
	private String nmSenha;
	
	private List<PerfilDTO> perfis = new ArrayList<>();
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nmUsuario, String nmEmail, String nmSenha) {
		super();
		this.id = id;
		this.nmUsuario = nmUsuario;
		this.nmEmail = nmEmail;
		this.nmSenha = nmSenha;
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nmUsuario = usuario.getNmUsuario();
		this.nmEmail = usuario.getNmEmail();
		this.nmSenha = usuario.getNmSenha();
	}
	
	public UsuarioDTO(Usuario usuario, Set<Perfil> perfis) {
		this(usuario);
		perfis.forEach(cat -> this.perfis.add(new PerfilDTO(cat)));
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

	public List<PerfilDTO> getPerfis() {
		return perfis;
	}
	
}
