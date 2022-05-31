package br.com.julio.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drogaria")
public class DrogariaService {
	
	@GET
	public String exibir() {
		return "Teste de Java";
	}
}
