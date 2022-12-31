package br.com.julio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Cidade;
import br.com.julio.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar() {

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(2L);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Julio Cesar Coutinho");
		pessoa.setCpf("36770357836");
		pessoa.setRg("456972444");
		pessoa.setEmail("julio@gemmap.com.br");
		pessoa.setCelular("14997599349");
		pessoa.setTelefone("1433449090");
		pessoa.setCep("18950039");
		pessoa.setRua("Rua João dos santos");
		pessoa.setBairro("Centro");
		pessoa.setNumero(new Short("1400"));
		pessoa.setcomplemente("Fundos");

		pessoa.setCidade(cidade);

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
		System.out.println("Pessoa Inserida com sucesso");

	}

// --------------------------------------------- Buscar --------------------------------------------------------------------
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 20L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		@SuppressWarnings("unused")
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		System.out.println("O resultado é: " + codigo);

	}
	
// --------------------------------------------- Listar ---------------------------------------------------------------------
	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		
		for (Pessoa pessoa : resultado);
			System.out.println("O Resultado é: ");
		}

// --------------------------------------------- Excluir --------------------------------------------------------------------	
	@Test
	@Ignore
	public void excluir() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(50l);
		
		pessoaDAO.excluir(pessoa);
		System.out.println("Pessoa Removida com Sucesso");
	}
	
}
