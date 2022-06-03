package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.CidadeDAO;
import br.com.julio.drogaria.dao.EstadoDAO;
import br.com.julio.drogaria.dao.FuncionarioDAO;
import br.com.julio.drogaria.dao.PessoaDAO;
import br.com.julio.drogaria.domain.Funcionario;
import br.com.julio.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/*
	 * =============================================== Listar
	 * ===============================================
	 */
	@PostConstruct
	public void iniciarTela() {
		lista();
	}
	
	public void lista() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários");
			erro.printStackTrace();
		}
	}

	/*
	 * =============================================== Novo
	 * ===============================================
	 */

	public void novo() {
		try {
			funcionario = new Funcionario();

			pessoa = new Pessoa();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			lista();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo funcionário");
			erro.printStackTrace();
		}
	}
	/*
	 * =============================================== Editar
	 * ===============================================
	 */

	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			lista();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao selecionar o funcionario");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			funcionario = new Funcionario();

			Messages.addGlobalInfo("Funcionário salvo com sucesso");
			
			lista();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao salvar um novo funcionário");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário excluido com sucesso");

			lista();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o funcionario");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (pessoa != null) {
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoas = pessoaDAO.listar();
			} else {
				pessoas = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

}
