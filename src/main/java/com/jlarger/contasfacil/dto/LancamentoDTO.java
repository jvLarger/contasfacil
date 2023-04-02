package com.jlarger.contasfacil.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.jlarger.contasfacil.entities.Categoria;
import com.jlarger.contasfacil.entities.ContaBancaria;
import com.jlarger.contasfacil.entities.Lancamento;
import com.jlarger.contasfacil.entities.type.LancamentoTipoNatureza;

public class LancamentoDTO {
	
	private Long id;
	private LancamentoTipoNatureza tpNatureza;
	private Integer nrParcela;
	private Double vlrMovimento;
	private Double vlrPago;
	private LocalDate dtVencimento;
	private LocalDate dtPagamento;
	private String nmInformacaoPrincipal;
	private String txtDetalhes;
	private ContaBancariaDTO contaBancaria;
	private CategoriaDTO categoria;
	
	public LancamentoDTO() {
	}

	public LancamentoDTO(Long id, LancamentoTipoNatureza tpNatureza, Integer nrParcela, Double vlrMovimento,
			Double vlrPago, LocalDate dtVencimento, LocalDate dtPagamento, String nmInformacaoPrincipal,
			String txtDetalhes, ContaBancaria contaBancaria, Categoria categoria) {
		super();
		this.id = id;
		this.tpNatureza = tpNatureza;
		this.nrParcela = nrParcela;
		this.vlrMovimento = vlrMovimento;
		this.vlrPago = vlrPago;
		this.dtVencimento = dtVencimento;
		this.dtPagamento = dtPagamento;
		this.nmInformacaoPrincipal = nmInformacaoPrincipal;
		this.txtDetalhes = txtDetalhes;
		this.contaBancaria = new ContaBancariaDTO(contaBancaria);
		this.categoria = new CategoriaDTO(categoria);
	}
	
	public LancamentoDTO(Lancamento lancamento) {
		super();
		this.id = lancamento.getId();
		this.tpNatureza = lancamento.getTpNatureza();
		this.nrParcela = lancamento.getNrParcela();
		this.vlrMovimento = lancamento.getVlrMovimento();
		this.vlrPago = lancamento.getVlrPago();
		this.dtVencimento = lancamento.getDtVencimento();
		this.dtPagamento = lancamento.getDtPagamento();
		this.nmInformacaoPrincipal = lancamento.getNmInformacaoPrincipal();
		this.txtDetalhes = lancamento.getTxtDetalhes();
		this.contaBancaria = new ContaBancariaDTO(lancamento.getContaBancaria());
		this.categoria = new CategoriaDTO(lancamento.getCategoria());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LancamentoTipoNatureza getTpNatureza() {
		return tpNatureza;
	}

	public void setTpNatureza(LancamentoTipoNatureza tpNatureza) {
		this.tpNatureza = tpNatureza;
	}

	public Integer getNrParcela() {
		return nrParcela;
	}

	public void setNrParcela(Integer nrParcela) {
		this.nrParcela = nrParcela;
	}

	public Double getVlrMovimento() {
		return vlrMovimento;
	}

	public void setVlrMovimento(Double vlrMovimento) {
		this.vlrMovimento = vlrMovimento;
	}

	public Double getVlrPago() {
		return vlrPago;
	}

	public void setVlrPago(Double vlrPago) {
		this.vlrPago = vlrPago;
	}

	public LocalDate getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public LocalDate getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(LocalDate dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public String getNmInformacaoPrincipal() {
		return nmInformacaoPrincipal;
	}

	public void setNmInformacaoPrincipal(String nmInformacaoPrincipal) {
		this.nmInformacaoPrincipal = nmInformacaoPrincipal;
	}

	public String getTxtDetalhes() {
		return txtDetalhes;
	}

	public void setTxtDetalhes(String txtDetalhes) {
		this.txtDetalhes = txtDetalhes;
	}

	public ContaBancariaDTO getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancariaDTO contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, contaBancaria, dtPagamento, dtVencimento, id, nmInformacaoPrincipal, nrParcela,
				tpNatureza, txtDetalhes, vlrMovimento, vlrPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LancamentoDTO other = (LancamentoDTO) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(contaBancaria, other.contaBancaria)
				&& Objects.equals(dtPagamento, other.dtPagamento) && Objects.equals(dtVencimento, other.dtVencimento)
				&& Objects.equals(id, other.id) && Objects.equals(nmInformacaoPrincipal, other.nmInformacaoPrincipal)
				&& Objects.equals(nrParcela, other.nrParcela) && tpNatureza == other.tpNatureza
				&& Objects.equals(txtDetalhes, other.txtDetalhes) && Objects.equals(vlrMovimento, other.vlrMovimento)
				&& Objects.equals(vlrPago, other.vlrPago);
	}
}