package br.com.julio.drogaria.dao;

import java.math.BigDecimal;

import org.hibernate.mapping.List;
import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;

public class ProdutoDAOTest {
//-------------------------------------	Salvar -----------------------------------------------------//
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(4L);

		Produto produto = new Produto();
		produto.setDescricao("Amoxilina 100mg cx com 50Un");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("20.00"));
		produto.setQuantidade(new Short("23"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

		System.out.println("Produto Inserido com sucesso");

	}

//------------------------------------Listar---------------------------------------------------------//	

	@Test
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
	}
	
}	
	