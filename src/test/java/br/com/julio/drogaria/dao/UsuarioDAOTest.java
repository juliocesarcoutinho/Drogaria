package br.com.julio.drogaria.dao;

import org.junit.Test;

import br.com.julio.drogaria.domain.Pessoa;
import br.com.julio.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(26l);
		
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setTipo('A');
		usuario.setSenha("q1w2e3r4");
		usuario.setPessoa(pessoa);
		
		System.out.println("Nome do Usuario:" + pessoa.getNome());
		System.out.println("CPF:" + pessoa.getCpf());
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	
	}
	
		
}
