package com.jlarger.contasfacil.entities;

import java.util.Objects;

import com.jlarger.contasfacil.entities.type.CartaoTipoCartao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CartaoTipoCartao tpCartao;
	
	@Column(nullable = false, length = 255)
	private String nmApelidoCartao;
	
	@Column(columnDefinition = "float8 DEFAULT 0.0")
	private Double vlrLimite;
	
	private Integer nrDiaVencimentoFatura;
	
	@Column(length = 2)
	private Integer nrDiaFechamentoFatura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_conta_bancaria", nullable = false)
	private ContaBancaria contaBancaria;
	
	public Cartao() {
	}

	public Cartao(Long id, CartaoTipoCartao tpCartao, String nmApelidoCartao, Double vlrLimite,
			Integer nrDiaVencimentoFatura, Integer nrDiaFechamentoFatura, Usuario usuario,
			ContaBancaria contaBancaria) {
		super();
		this.id = id;
		this.tpCartao = tpCartao;
		this.nmApelidoCartao = nmApelidoCartao;
		this.vlrLimite = vlrLimite;
		this.nrDiaVencimentoFatura = nrDiaVencimentoFatura;
		this.nrDiaFechamentoFatura = nrDiaFechamentoFatura;
		this.usuario = usuario;
		this.contaBancaria = contaBancaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CartaoTipoCartao getTpCartao() {
		return tpCartao;
	}

	public void setTpCartao(CartaoTipoCartao tpCartao) {
		this.tpCartao = tpCartao;
	}

	public String getNmApelidoCartao() {
		return nmApelidoCartao;
	}

	public void setNmApelidoCartao(String nmApelidoCartao) {
		this.nmApelidoCartao = nmApelidoCartao;
	}

	public Double getVlrLimite() {
		return vlrLimite;
	}

	public void setVlrLimite(Double vlrLimite) {
		this.vlrLimite = vlrLimite;
	}

	public Integer getNrDiaVencimentoFatura() {
		return nrDiaVencimentoFatura;
	}

	public void setNrDiaVencimentoFatura(Integer nrDiaVencimentoFatura) {
		this.nrDiaVencimentoFatura = nrDiaVencimentoFatura;
	}

	public Integer getNrDiaFechamentoFatura() {
		return nrDiaFechamentoFatura;
	}

	public void setNrDiaFechamentoFatura(Integer nrDiaFechamentoFatura) {
		this.nrDiaFechamentoFatura = nrDiaFechamentoFatura;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contaBancaria, id, nmApelidoCartao, nrDiaFechamentoFatura, nrDiaVencimentoFatura, tpCartao,
				usuario, vlrLimite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(contaBancaria, other.contaBancaria) && Objects.equals(id, other.id)
				&& Objects.equals(nmApelidoCartao, other.nmApelidoCartao)
				&& Objects.equals(nrDiaFechamentoFatura, other.nrDiaFechamentoFatura)
				&& Objects.equals(nrDiaVencimentoFatura, other.nrDiaVencimentoFatura) && tpCartao == other.tpCartao
				&& Objects.equals(usuario, other.usuario) && Objects.equals(vlrLimite, other.vlrLimite);
	}
	
}
