package br.com.julio.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Caixa extends GenericDomain {

	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorAbertura;

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValor() {
		return valorAbertura;
	}

	public void setValor(BigDecimal valor) {
		this.valorAbertura = valor;
	}

}
