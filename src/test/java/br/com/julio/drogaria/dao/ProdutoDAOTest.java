package br.com.julio.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);
		
		
		Produto produto = new Produto();
		produto.setDescricao("Tandifram Caixa com 30 UN");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.50"));
		produto.setQuantidade(new Short("8"));
	
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto inserido");
	
	}
	
	
}
