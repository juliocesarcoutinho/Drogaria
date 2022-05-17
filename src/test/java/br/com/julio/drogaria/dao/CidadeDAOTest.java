package br.com.julio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Cidade;
import br.com.julio.drogaria.domain.Estado;

//----------------------------- Salvar -----------------------------------------//
public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(2L);

		Cidade cidade = new Cidade();
		cidade.setNome("Rio de Janeiro");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);

	}

// ------------------------------ Listar --------------------------------------//
	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();

		for (Cidade cidade : resultado) {
			System.out.println("Código da Cidade: " + cidade.getCodigo());
			System.out.println("Nome da Cidade: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
			System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
			System.out.println();
		}

	}

//------------------------------ Buscar --------------------------------------//
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 8L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
		System.out.println();

	}

// -----------------------------Excluir -------------------------------------//
	@Test
	public void excluir() {
		Long codigo = 20L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		if(cidade == null){
			System.out.println("Registro não encontrado");
		} else {
			cidadeDAO.excluir(cidade);
			System.out.println("Registro Removido");
		}
		
		cidadeDAO.excluir(cidade);
		
		System.out.println("Cidade Removida");
		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
		System.out.println();
	}
	@Test
	public void editar (){
		Long codigo = 9L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		
		
	}
}
