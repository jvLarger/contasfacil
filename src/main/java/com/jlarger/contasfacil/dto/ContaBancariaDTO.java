package com.jlarger.contasfacil.dto;

import java.util.Objects;

import com.jlarger.contasfacil.entities.Banco;
import com.jlarger.contasfacil.entities.ContaBancaria;

public class ContaBancariaDTO {
	
	private Long id;
	private String nmNumeroConta;
	private Double vlrLimiteConta;
	private String txtDetalhes;
    private Banco banco;
	
    public ContaBancariaDTO() {
	}

	public ContaBancariaDTO(Long id, String nmNumeroConta, Double vlrLimiteConta, String txtDetalhes, Banco banco) {
		this.id = id;
		this.nmNumeroConta = nmNumeroConta;
		this.vlrLimiteConta = vlrLimiteConta;
		this.txtDetalhes = txtDetalhes;
		this.banco = banco;
	}
	
	public ContaBancariaDTO(ContaBancaria contaBancaria) {
		this.id = contaBancaria.getId();
		this.nmNumeroConta = contaBancaria.getNmNumeroConta();
		this.vlrLimiteConta = contaBancaria.getVlrLimiteConta();
		this.txtDetalhes = contaBancaria.getTxtDetalhes();
		this.banco = contaBancaria.getBanco();
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

	@Override
	public int hashCode() {
		return Objects.hash(banco, id, nmNumeroConta, txtDetalhes, vlrLimiteConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancariaDTO other = (ContaBancariaDTO) obj;
		return Objects.equals(banco, other.banco) && Objects.equals(id, other.id)
				&& Objects.equals(nmNumeroConta, other.nmNumeroConta) && Objects.equals(txtDetalhes, other.txtDetalhes)
				&& Objects.equals(vlrLimiteConta, other.vlrLimiteConta);
	}
	
}