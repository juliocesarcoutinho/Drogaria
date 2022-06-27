package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.PessoaDAO;
import br.com.julio.drogaria.dao.UsuarioDAO;
import br.com.julio.drogaria.domain.Pessoa;
import br.com.julio.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;

	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	public List<Usuario> getUsuarios() {
		return usuarios;

	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;

	}

	public List<Pessoa> getPessoas() {
		return pessoas;

	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;

	}

	/************************* Listar *********************/
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("tipoUsuario");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}

	/************************** Novo **********************/
	public void novo() {
		try {
			
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	/**************************** Salvar **********************/
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuario = new Usuario();
			usuarios = usuarioDAO.listar("tipoUsuario");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Usuario Salvo Com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("usuarioSalvoSucesso"));
		}
	}

	/**************************** Editar ************************/
	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um usuario");
			erro.printStackTrace();
		}

	}

	/*************************** Excluir **************************/

	public void excluir(ActionEvent evento) {
		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuario excluido com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Excluir Usuario");
			erro.printStackTrace();
		}
	}

}
