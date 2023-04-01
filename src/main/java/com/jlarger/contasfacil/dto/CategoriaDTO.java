package com.jlarger.contasfacil.dto;

import java.io.Serializable;

import com.jlarger.contasfacil.entities.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nmCategoria;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nmCategoria) {
		super();
		this.id = id;
		this.nmCategoria = nmCategoria;
	}
	
	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nmCategoria = categoria.getNmCategoria();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmCategoria() {
		return nmCategoria;
	}

	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}
	
}