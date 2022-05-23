package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.EstadoDAO;
import br.com.julio.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	
	private List<Estado> estados;
	

	public Estado getEstado() {
		return estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar os Estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);

			estado = new Estado();
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado Salvo com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar o Estado");
			erro.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Nome: " + estado.getNome() + "Sigla" + estado.getSigla());
	}
	
	
}
