package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.FabricanteDAO;
import br.com.julio.drogaria.dao.ProdutoDAO;
import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	@PostConstruct
	public void listar() {
		try {
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar Produtos");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void novo() {
		try {
			
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar o Produto");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void editar (ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricantes = fabricanteDAO.listar();
		
	}
	
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			
			produto = new Produto();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			produtos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Produto salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o produto");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			produtos = produtoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o Produto");
			erro.printStackTrace();
		}
	}
}
