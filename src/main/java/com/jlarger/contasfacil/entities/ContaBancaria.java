package com.jlarger.contasfacil.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(	name = "conta_bancaria")
public class ContaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length = 40)
	private String nmNumeroConta;
	
	@Column(nullable=false, length = 40)
	private Double vlrLimiteConta;
	
	@Column(nullable=false, length = 40)
	private String txtDetalhes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_banco", nullable=false)
    private Banco banco;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable=false)
    private Usuario usuario;
	
	public ContaBancaria() {
	}

	public ContaBancaria(Long id, String nmNumeroConta, Double vlrLimiteConta, String txtDetalhes, Banco banco, Usuario usuario) {
		super();
		this.id = id;
		this.nmNumeroConta = nmNumeroConta;
		this.vlrLimiteConta = vlrLimiteConta;
		this.txtDetalhes = txtDetalhes;
		this.banco = banco;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmNumeroConta() {
		return nmNumeroConta;
	}

	public void setNmNumeroConta(String nmNumeroConta) {
		this.nmNumeroConta = nmNumeroConta;
	}

	public Double getVlrLimiteConta() {
		return vlrLimiteConta;
	}

	public void setVlrLimiteConta(Double vlrLimiteConta) {
		this.vlrLimiteConta = vlrLimiteConta;
	}

	public String getTxtDetalhes() {
		return txtDetalhes;
	}

	public void setTxtDetalhes(String txtDetalhes) {
		this.txtDetalhes = txtDetalhes;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banco, id, nmNumeroConta, txtDetalhes, usuario, vlrLimiteConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		return Objects.equals(banco, other.banco) && Objects.equals(id, other.id)
				&& Objects.equals(nmNumeroConta, other.nmNumeroConta) && Objects.equals(txtDetalhes, other.txtDetalhes)
				&& Objects.equals(usuario, other.usuario) && Objects.equals(vlrLimiteConta, other.vlrLimiteConta);
	}
	
}