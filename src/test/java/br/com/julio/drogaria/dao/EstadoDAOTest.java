package br.com.julio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	//@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Paraná");
		estado.setSigla("PR");
		

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		for (Estado estado : resultado) {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		System.out.println(estado.getCodigo());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		estadoDAO.excluir(estado);

		System.out.println(estado.getCodigo());
	}
	@Test
	@Ignore
	public void editar() {
		Long codigo = 4L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		estado.setSigla("SC");
		estado.setNome("Santa Catarina");
		estadoDAO.editar(estado);
	}
}
