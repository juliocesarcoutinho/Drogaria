package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.PessoaDAO;
import br.com.julio.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

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
		pessoa = new Pessoa();
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

			Messages.addGlobalInfo("Pessoa Salva com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir a pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

	}
}
