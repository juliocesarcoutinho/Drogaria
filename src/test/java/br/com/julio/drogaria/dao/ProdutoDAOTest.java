package br.com.julio.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

//import org.hibernate.mapping.List;
import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;

public class ProdutoDAOTest {
//-------------------------------------	Salvar -----------------------------------------------------//
	@Test
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(11L);

		Produto produto = new Produto();
		produto.setDescricao("Tandifram 100mg Cx C/50Un");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("35.10"));
		produto.setQuantidade(new Short("6"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

		System.out.println("Produto Inserido com sucesso");

	}

//------------------------------------ Buscar ---------------------------------------------------------//	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 16L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto estado = produtoDAO.buscar(codigo);

		System.out.println(" Produto: " + estado.getCodigo());
	}
//------------------------------------ Listar ---------------------------------------------------------//	

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();

		for (Produto produto : resultado) {
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Nome do Produto: " + produto.getDescricao());
			System.out.println("Quantidade de estoque: " + produto.getQuantidade());
			System.out.println("Valor do Produto: " + produto.getPreco());
			System.out.println();

		}

	}

//------------------------------------ Editar ----------------------------------------------------//
	@Test
	@Ignore
	public void editar() {
		Long codigoProduto = 19L;
		Long codigoFabricante = 4L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		if (produto == null) {
			System.out.println("Nenhum Registro encontrado");
		} else {
			System.out.println("Registro editado - antes :");
			System.out.println(produto.getCodigo() + " - " + produto.getDescricao());
		}

		produto.setDescricao("Amoxilina 20Gr 40cps");
		produto.setQuantidade(new Short("6"));
		produto.setPreco(new BigDecimal("9.50"));
		produto.setFabricante(fabricante);
		produtoDAO.editar(produto);

	}

//------------------------------------- Excluir -----------------------------------------------//
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 20L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);

		if (produto == null) {
			System.out.println("Registro não encontrado");
		} else {
			produtoDAO.excluir(produto);
			System.out.println("Produto Removido com Sucesso");
		}

		produtoDAO.excluir(produto);

		System.out.println("Produto Removido");
		System.out.println();
	}
}
