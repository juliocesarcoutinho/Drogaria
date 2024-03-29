package br.com.julio.drogaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.UsuarioDAO;
import br.com.julio.drogaria.domain.Pessoa;
import br.com.julio.drogaria.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	@PostConstruct
	public void inicar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar() {
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			if(usuarioLogado == null) {
				Messages.addGlobalError("Dados inseridos incorretos");
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
		}catch (IOException erro) {
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		}
		
	}
//	public boolean temPermissoes(List<String> permissoes){
//		
//		for(String permissao : permissoes) {
//			if(usuarioLogado.getTipo() == permissao.charAt(0)) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
}
