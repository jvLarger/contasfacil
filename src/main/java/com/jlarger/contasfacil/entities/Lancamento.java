package com.jlarger.contasfacil.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.jlarger.contasfacil.entities.type.LancamentoTipoNatureza;

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
@Table(name = "lancamento")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "ind_tp_natureza")
	@Enumerated(EnumType.ORDINAL)
	private LancamentoTipoNatureza tpNatureza;

	@Column(nullable = false)
	private Integer nrParcela;

	@Column(nullable = false, columnDefinition = "float8 DEFAULT 0.0")
	private Double vlrMovimento;

	@Column(nullable = false, columnDefinition = "float8 DEFAULT 0.0")
	private Double vlrPago;

	@Column(nullable = false)
	private LocalDate dtVencimento;

	private LocalDate dtPagamento;

	@Column(nullable = false, length = 255)
	private String nmInformacaoPrincipal;

	@Column(columnDefinition = "TEXT")
	private String txtDetalhes;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_conta_bancaria", nullable = false)
	private ContaBancaria contaBancaria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	public Lancamento() {
	}

	public Lancamento(Long id, LancamentoTipoNatureza tpNatureza, Integer nrParcela, Double vlrMovimento,
			Double vlrPago, LocalDate dtVencimento, LocalDate dtPagamento, String nmInformacaoPrincipal,
			String txtDetalhes, ContaBancaria contaBancaria, Categoria categoria, Usuario usuario) {
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
		this.contaBancaria = contaBancaria;
		this.categoria = categoria;
		this.usuario = usuario;
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

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, contaBancaria, dtPagamento, dtVencimento, id, nmInformacaoPrincipal, nrParcela,
				tpNatureza, txtDetalhes, usuario, vlrMovimento, vlrPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(contaBancaria, other.contaBancaria)
				&& Objects.equals(dtPagamento, other.dtPagamento) && Objects.equals(dtVencimento, other.dtVencimento)
				&& Objects.equals(id, other.id) && Objects.equals(nmInformacaoPrincipal, other.nmInformacaoPrincipal)
				&& Objects.equals(nrParcela, other.nrParcela) && tpNatureza == other.tpNatureza
				&& Objects.equals(txtDetalhes, other.txtDetalhes) && Objects.equals(usuario, other.usuario)
				&& Objects.equals(vlrMovimento, other.vlrMovimento) && Objects.equals(vlrPago, other.vlrPago);
	}

}