package br.com.julio.drogaria.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.julio.drogaria.dao.CaixaDAO;
import br.com.julio.drogaria.dao.FuncionarioDAO;
import br.com.julio.drogaria.domain.Caixa;
import br.com.julio.drogaria.domain.Funcionario;

@ManagedBean
@ViewScoped
public class caixaBean {
	private ScheduleModel caixas;
	private Caixa caixa;
	private List<Funcionario> funcionarios;
	
	public ScheduleModel getCaixas() {
		return caixas;
	}
	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}
	public Caixa getCaixa() {
		return caixa;
	}
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}
	
	public void novo(SelectEvent evento) {		
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
	}
	public void salvar() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(Calendar.DATE, 1);
		caixa.setDataAbertura(calendar.getTime());
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		Messages.addGlobalInfo("Caixa Aberto com sucesso");
	}
}
