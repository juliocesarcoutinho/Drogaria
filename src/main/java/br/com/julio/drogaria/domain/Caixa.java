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
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valor;
	
	
}
