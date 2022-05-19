package br.com.julio.drogaria.dao;

import org.junit.Test;

import br.com.julio.drogaria.domain.Cidade;
import br.com.julio.drogaria.domain.Cliente;
import br.com.julio.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	public void salvar(){
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(5L);
		
		
		Pessoa pessoa = new Pessoa();
		pessoa .setNome("Rebekah Olivia Aquino Coutinho");
		pessoa.setCpf("11111111111");
		pessoa.setRg("000000000");
		pessoa.setEmail("azedinha@gmail.com");
		pessoa.setCelular("14997599349");
		pessoa.setTelefone("1433449090");
		pessoa.setCep("18950009");
		pessoa.setRua("Professor Antônio Martins");
		pessoa.setBairro("Vila Melgis");
		pessoa.setNumero(new Short("146"));
		pessoa.setComplemente("");
		
		pessoa.setCidade(cidade);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
	    pessoaDAO.salvar(pessoa);
		System.out.println("Pessoa Inserida com sucesso");
		
	}

// --------------------------------------------- Buscar --------------------------------------------------------------------
		public void buscar() {
			Long codigo = 20L;
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(codigo);
			
			System.out.println("");
			
			
		}
}
