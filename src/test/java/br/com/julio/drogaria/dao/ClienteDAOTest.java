package br.com.julio.drogaria.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Cliente;
import br.com.julio.drogaria.domain.Pessoa;

public class ClienteDAOTest {

// -------------------------------------------- Salvar ------------------------------------------	
	@Test
	@Ignore
	public void salvar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(21L);

		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Cliente Inserido Com Sucesso");
	}

// -------------------------------------------- Buscar --------------------------------------------
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 23L;

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);

		System.out.println(" O nome do cliente é: " + cliente.getCodigo());
	}

// --------------------------------------------- Listar --------------------------------------------
	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();

		for (Cliente cliente : resultado) {
			System.out.println(cliente.getCodigo());
		}
	}
	
// --------------------------------------------- Editar ---------------------------------------------
	@Test
	@Ignore
	public void editar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(25l);
		
		cliente.setLiberado(false);
		clienteDAO.editar(cliente);
		
	}
// ---------------------------------------------- Excluir -------------------------------------------
	@Test
	public void excluir() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(25l);
		
		clienteDAO.excluir(cliente);
		
		System.out.println("Cliente Removido com Sucesso");
	}
	
}
