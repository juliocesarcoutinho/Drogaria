package br.com.julio.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.julio.drogaria.enumeracao.TipoUsuario;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	
//	@Column(nullable = false)
//	private Character tipo;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

//	public Character getTipo() {
//		return tipo;
//	}
//	@Transient
//	public String getTipoFormatado() {
//		String tipoFormatado = null;
//		
//		if(tipo == 'A') {
//			tipoFormatado = "Administrador";
//		}else if (tipo == 'B') {
//			tipoFormatado = "Balconista";
//		}else if (tipo == 'G') {
//			tipoFormatado = "Gerente";
//		}
//		
//		return tipoFormatado;
//	}
//
//	public void setTipo(Character tipo) {
//		this.tipo = tipo;
//	}

	public Boolean getAtivo() {
		return ativo;
	}
	
	public String ativoFormatado() {
		String ativoFormatado = null;
		if(ativo) {
			ativoFormatado = "Sim";
		}else {
			ativoFormatado = "Não";
		}
		
		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
