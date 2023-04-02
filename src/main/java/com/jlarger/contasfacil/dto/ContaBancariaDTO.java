package com.jlarger.contasfacil.dto;

import java.util.Objects;

import com.jlarger.contasfacil.entities.Banco;
import com.jlarger.contasfacil.entities.ContaBancaria;

public class ContaBancariaDTO {

	private Long id;
	private String nmNumeroConta;
	private String nmApelidoConta;
	private String txtDetalhes;
	private Double vlrSaldoAtual;
	private BancoDTO banco;

	public ContaBancariaDTO() {
	}

	public ContaBancariaDTO(Long id, String nmNumeroConta, String nmApelidoConta, String txtDetalhes, Banco banco, Double vlrSaldoAtual) {
		this.id = id;
		this.nmNumeroConta = nmNumeroConta;
		this.nmApelidoConta = nmApelidoConta;
		this.txtDetalhes = txtDetalhes;
		this.vlrSaldoAtual = vlrSaldoAtual;
		this.banco = new BancoDTO(banco);
	}

	public ContaBancariaDTO(ContaBancaria contaBancaria) {
		this.id = contaBancaria.getId();
		this.nmNumeroConta = contaBancaria.getNmNumeroConta();
		this.nmApelidoConta = contaBancaria.getNmApelidoConta();
		this.txtDetalhes = contaBancaria.getTxtDetalhes();
		this.vlrSaldoAtual = contaBancaria.getVlrSaldoAtual();
		this.banco = new BancoDTO(contaBancaria.getBanco());
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

	public String getNmApelidoConta() {
		return nmApelidoConta;
	}

	public void setNmApelidoConta(String nmApelidoConta) {
		this.nmApelidoConta = nmApelidoConta;
	}

	public String getTxtDetalhes() {
		return txtDetalhes;
	}

	public void setTxtDetalhes(String txtDetalhes) {
		this.txtDetalhes = txtDetalhes;
	}

	public BancoDTO getBanco() {
		return banco;
	}

	public Double getVlrSaldoAtual() {
		return vlrSaldoAtual;
	}

	public void setVlrSaldoAtual(Double vlrSaldoAtual) {
		this.vlrSaldoAtual = vlrSaldoAtual;
	}

	public void setBanco(BancoDTO banco) {
		this.banco = banco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banco, id, nmNumeroConta, txtDetalhes);
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
				&& Objects.equals(nmNumeroConta, other.nmNumeroConta) && Objects.equals(txtDetalhes, other.txtDetalhes);
	}

}