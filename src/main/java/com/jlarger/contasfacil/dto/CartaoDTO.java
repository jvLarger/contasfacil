package com.jlarger.contasfacil.dto;

import java.util.Objects;

import com.jlarger.contasfacil.entities.Cartao;
import com.jlarger.contasfacil.entities.ContaBancaria;
import com.jlarger.contasfacil.entities.type.CartaoTipoCartao;

public class CartaoDTO {
	
	private Long id;
	private CartaoTipoCartao tpCartao;
	private String nmApelidoCartao;
	private Double vlrLimite;
	private Integer nrDiaVencimentoFatura;
	private Integer nrDiaFechamentoFatura;
	private ContaBancariaDTO contaBancaria;
	
	public CartaoDTO() {
	}
	
	public CartaoDTO(Long id, CartaoTipoCartao tpCartao, String nmApelidoCartao, Double vlrLimite,
			Integer nrDiaVencimentoFatura, Integer nrDiaFechamentoFatura, ContaBancaria contaBancaria) {
		super();
		this.id = id;
		this.tpCartao = tpCartao;
		this.nmApelidoCartao = nmApelidoCartao;
		this.vlrLimite = vlrLimite;
		this.nrDiaVencimentoFatura = nrDiaVencimentoFatura;
		this.nrDiaFechamentoFatura = nrDiaFechamentoFatura;
		this.contaBancaria = new ContaBancariaDTO(contaBancaria);
	}
	
	public CartaoDTO(Cartao cartao) {
		super();
		this.id = cartao.getId();
		this.tpCartao = cartao.getTpCartao();
		this.nmApelidoCartao = cartao.getNmApelidoCartao();
		this.vlrLimite = cartao.getVlrLimite();
		this.nrDiaVencimentoFatura = cartao.getNrDiaVencimentoFatura();
		this.nrDiaFechamentoFatura = cartao.getNrDiaFechamentoFatura();
		this.contaBancaria = new ContaBancariaDTO(cartao.getContaBancaria());
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

	public ContaBancariaDTO getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancariaDTO contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contaBancaria, id, nmApelidoCartao, nrDiaFechamentoFatura, nrDiaVencimentoFatura, tpCartao,
				vlrLimite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoDTO other = (CartaoDTO) obj;
		return Objects.equals(contaBancaria, other.contaBancaria) && Objects.equals(id, other.id)
				&& Objects.equals(nmApelidoCartao, other.nmApelidoCartao)
				&& Objects.equals(nrDiaFechamentoFatura, other.nrDiaFechamentoFatura)
				&& Objects.equals(nrDiaVencimentoFatura, other.nrDiaVencimentoFatura) && tpCartao == other.tpCartao
				&& Objects.equals(vlrLimite, other.vlrLimite);
	}
	
}