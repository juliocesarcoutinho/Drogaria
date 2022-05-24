package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import br.com.julio.drogaria.dao.CidadeDAO;
import br.com.julio.drogaria.domain.Cidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	@PostConstruct ()
	public void listar() {
		try {
			CidadeDAO cidadeDAO= new CidadeDAO();
			cidades = cidadeDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro Ao Listar as Cidades");
			erro.printStackTrace();
		}
		
	}
	
	public void novo() {
		cidade = new Cidade();
	}
}
