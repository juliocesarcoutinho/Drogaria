package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.FabricanteDAO;
import br.com.julio.drogaria.dao.ProdutoDAO;
import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2  implements Serializable{
	
	private Produto produto;
	private Long codigoProduto;
	
	private List<Fabricante> fabricantes;
	private List<Produto> produtos;
	
	private ProdutoDAO produtoDAO;
	private FabricanteDAO fabricanteDAO;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public Long getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	
	@PostConstruct
	public void iniciar() {
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
	}
	
	public void listar() {
		try {
			
			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar Produtos");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}
	public void carregarEdicao() {
		try {
			produto = produtoDAO.buscar(codigoProduto);
			
			fabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao carregar dados para a EDIÇÃO");
		}
	}
	
	public void salvarEdicao() {
		try {
			produto = produtoDAO.merge(produto);
			
			fabricantes = fabricanteDAO.listar();
			
			Messages.addGlobalInfo("Produto Alterado com Sucesso");
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Salvar o Produto");
		}
		
		
	}
	
 }
