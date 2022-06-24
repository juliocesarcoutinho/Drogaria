package br.com.julio.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Pessoa;
import br.com.julio.drogaria.domain.Usuario;
import br.com.julio.drogaria.enumeracao.TipoUsuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(36L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
				
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		usuario.setSenhaSemCriptografia("147258");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		
		usuario.setSenha(hash.toHex());
		usuario.setPessoa(pessoa);
		
		System.out.println("Nome do Usuario:" + pessoa.getNome());
		System.out.println("CPF:" + pessoa.getCpf());
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	
	}
	@Test
	@Ignore
	public void autenticar() {
		String cpf = "367.703.578-36";
		String senha = "q1w2e3r4";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuario autenticado: " + usuario);
	}
	
		
}
