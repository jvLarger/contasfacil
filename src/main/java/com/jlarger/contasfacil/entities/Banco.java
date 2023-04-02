package com.jlarger.contasfacil.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(	name = "banco")
public class Banco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length = 3)
	private String codigoNacional;
	
	@Column(nullable=false, length = 255)
	private String nmBanco;
	
	@OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContaBancaria> contasBancarias = new ArrayList<>();
	
	public Banco() {
	}

	public Banco(Long id, String codigoNacional, String nmBanco) {
		this.id = id;
		this.codigoNacional = codigoNacional;
		this.nmBanco = nmBanco;
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

	@Override
	public int hashCode() {
		return Objects.hash(codigoNacional, id, nmBanco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banco other = (Banco) obj;
		return Objects.equals(codigoNacional, other.codigoNacional) && Objects.equals(id, other.id)
				&& Objects.equals(nmBanco, other.nmBanco);
	}
	
}