package br.com.julio.drogaria.dao;

import org.junit.Test;

import br.com.julio.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	public void salvar(){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Julio Cesar Coutinho");
		pessoa.setCpf("36770357836");
		pessoa.setRg("456972444");
		pessoa.setEmail("julio@gemmap.com.br");
		pessoa.setCelular("14997568439");
		pessoa.setTelefone("1433449090");
		pessoa.setCep("18950009");
		pessoa.setRua("Professor Antônio Martins");
		pessoa.setBairro("Vila Melgis");
		pessoa.setNumero(new Short("146"));
		pessoa.setComplemente("Casa");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
	    pessoaDAO.salvar(pessoa);
		System.out.println("Pessoa Inserida com sucesso");
		
	} 
}
