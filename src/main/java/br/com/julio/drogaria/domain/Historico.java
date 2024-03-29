package br.com.julio.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Historico extends GenericDomain {
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = true, length = 500)
	private String observações;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public String getObservações() {
		return observações;
	}
	public void setObservações(String observações) {
		this.observações = observações;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
