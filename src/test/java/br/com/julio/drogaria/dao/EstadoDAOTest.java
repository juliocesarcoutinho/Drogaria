package br.com.julio.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar( ) {
		Estado estado = new Estado();
		estado.setNome("Rio De Janeiro");
		estado.setSigla("RJ");
		
		EstadoDAO estadoDAO =new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	@Test
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		for(Estado estado : resultado){
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}
	}
}
