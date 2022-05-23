package br.com.julio.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.julio.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Eurofarma");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}
	
		@Test
		@Ignore
		public void merge() {
//			Fabricante fabricante = new Fabricante();
//			fabricante.setDescricao("PharmaNew");
//			
//			FabricanteDAO fabricanteDAO = new FabricanteDAO();
//			fabricanteDAO.merge(fabricante);
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.buscar(51L);
			fabricante.setDescricao("NewPharma4");
			fabricanteDAO.merge(fabricante);
		}
}