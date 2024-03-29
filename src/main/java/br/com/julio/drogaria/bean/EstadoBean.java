package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.EstadoDAO;
import br.com.julio.drogaria.domain.Estado;
import br.com.julio.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;

	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar os Estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			estado = new Estado();
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado Salvo com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar o Estado");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado Excluido com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Excluir o Estado");
			erro.printStackTrace();
		}

	}
	
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		
	}
	
	public void imprimir() {
		try {
			String caminho = Faces.getRealPath("/reports/estados.jasper");

			Map<String, Object> parametros = new HashMap<>();

			Connection conexao = HibernateUtil.novaConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}

}
