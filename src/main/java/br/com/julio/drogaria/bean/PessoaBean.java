package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.EstadoDAO;
import br.com.julio.drogaria.dao.PessoaDAO;
import br.com.julio.drogaria.domain.Cidade;
import br.com.julio.drogaria.domain.Estado;
import br.com.julio.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Estado> estados;

	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;

	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;

	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;

	}

	public void setEstado(Estado estado) {
		this.estado = estado;

	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	// Ações //
	@PostConstruct
	public void listar() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar Pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			pessoa = new Pessoa();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoa = new Pessoa();
			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Pessoa salva com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar pessoa");
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			Messages.addGlobalInfo("Pessoa excluida com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir a pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

	}
	
	public void popular() {
		if(estado != null) {
			
		}
	}

}
