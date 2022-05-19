package br.com.julio.drogaria.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Funcionario;
import br.com.julio.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {

// ------------------------------------------- Salvar ----------------------------------------------------------------	
	@Test
	@Ignore
	public void salvar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(26l);

		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("12345678954");
		funcionario.setDataAdmissao(new Date());
		funcionario.setPessoa(pessoa);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);

		System.out.println("Funcionario Inserido Com Sucesso");

	}

// ------------------------------------------ Buscar --------------------------------------------------------------------
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 27L;

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(26L);

		System.out.println("A funcionaria mais Linda do Papai");
	}

// ------------------------------------------ Listar --------------------------------------------------------------------
	@Test
	@Ignore
	public void listar() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();

		for (Funcionario funcionario : resultado) {
			System.out.println(funcionario.getCodigo());

		}
	}

// ----------------------------------------- Editar ---------------------------------------------------------------------
	@Test
	@Ignore
	public void editar() {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(27l);
				
		funcionario.setCarteiraTrabalho("987654321");
		funcionarioDAO.editar(funcionario);
	}
	
// ----------------------------------------- Excluir --------------------------------------------------------------------
	@Test
	
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(27L);
		
		funcionarioDAO.excluir(funcionario);
	}
}
