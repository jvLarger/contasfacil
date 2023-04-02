package com.jlarger.contasfacil.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.jlarger.contasfacil.entities.type.FaturaSituacaoFatura;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fatura")
public class Fatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "ind_situacao_fatura")
	@Enumerated(EnumType.ORDINAL)
	private FaturaSituacaoFatura situacaoFatura;
	
	@Column(nullable = false, length = 2)
	private Integer nrMes;

	@Column(nullable = false, length = 2)
	private Integer nrAno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cartao", nullable = false)
	private Cartao cartao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(
            name = "fatura_lancamento",
            joinColumns = @JoinColumn(name = "id_fatura"),
            inverseJoinColumns = @JoinColumn(name = "id_lancamento"))
    private List<Lancamento> lancamentos = new ArrayList<>();
	
	public Fatura() {
	}

	public Fatura(Long id, FaturaSituacaoFatura situacaoFatura, Integer nrMes, Integer nrAno, Cartao cartao,
			Usuario usuario, List<Lancamento> lancamentos) {
		super();
		this.id = id;
		this.situacaoFatura = situacaoFatura;
		this.nrMes = nrMes;
		this.nrAno = nrAno;
		this.cartao = cartao;
		this.usuario = usuario;
		this.lancamentos = lancamentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FaturaSituacaoFatura getSituacaoFatura() {
		return situacaoFatura;
	}

	public void setSituacaoFatura(FaturaSituacaoFatura situacaoFatura) {
		this.situacaoFatura = situacaoFatura;
	}

	public Integer getNrMes() {
		return nrMes;
	}

	public void setNrMes(Integer nrMes) {
		this.nrMes = nrMes;
	}

	public Integer getNrAno() {
		return nrAno;
	}

	public void setNrAno(Integer nrAno) {
		this.nrAno = nrAno;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartao, id, nrAno, nrMes, situacaoFatura, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fatura other = (Fatura) obj;
		return Objects.equals(cartao, other.cartao) && Objects.equals(id, other.id)
				&& Objects.equals(nrAno, other.nrAno) && Objects.equals(nrMes, other.nrMes)
				&& situacaoFatura == other.situacaoFatura && Objects.equals(usuario, other.usuario);
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}