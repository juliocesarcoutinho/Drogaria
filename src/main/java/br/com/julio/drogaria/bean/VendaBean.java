package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.ClienteDAO;
import br.com.julio.drogaria.dao.FuncionarioDAO;
import br.com.julio.drogaria.dao.ProdutoDAO;
import br.com.julio.drogaria.dao.VendaDAO;
import br.com.julio.drogaria.domain.Cliente;
import br.com.julio.drogaria.domain.Funcionario;
import br.com.julio.drogaria.domain.ItemVenda;
import br.com.julio.drogaria.domain.Produto;
import br.com.julio.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda;

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Venda> vendas;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	/*
	 * =============================================================================
	 * ===================== Listar produtos na dataTable ==========================
	 * =============================================================================
	 */

	public void novo() {
		try {

			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}
		/*
		 * ===========================================================================
		 * ======================== Listagem de vendas ===============================
		 * ===========================================================================
		 * ===
		 */
		public void listar() {
			try {
				VendaDAO vendaDAO = new VendaDAO();
				 vendas = vendaDAO.listar("horario");
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao listar as vendas");
			}		
	}

	/*
	 * =============================================================================
	 * ================== Adicionar produtos na dataTable ==========================
	 * =============================================================================
	 */
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProdutos().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {

			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProdutos(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();		

	}
	
	public void atualizarValorParcial() {
		for(ItemVenda itemVenda : this.itensVenda) {
			itemVenda.setValorParcial(itemVenda.getProdutos().getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		
		this.calcular();
	}
	
	/*
	 * =============================================================================
	 * =============== Remover todos os produtos na dataTable ======================
	 * =============================================================================
	 */
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("botaoSelecionado");

		int achou = -1;

		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getProdutos().equals(itemVenda.getProdutos())) {
				achou = i;
			}
		}

		itensVenda.remove(achou);
		calcular();
	}

	/*
	 * =============================================================================
	 * ================== Remover item produtos na dataTable =======================
	 * =============================================================================
	 */
	public void diminuir(ActionEvent evento) {
		ItemVenda itemVendaEvento = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;

		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getProdutos().equals(itemVendaEvento.getProdutos())) {
				achou = i;
			}
		}

		if (achou >= 0) {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1 + ""));
			itemVenda.setValorParcial(itemVenda.getValorParcial().subtract(itemVenda.getProdutos().getPreco()));

			if (itemVenda.getQuantidade() <= 0) {
				itensVenda.remove(achou);
			}
		}

		calcular();
	}
	/*
	 * =============================================================================
	 * ========================== Calcular* Venda ==================================
	 * =============================================================================
	 */

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getValorParcial()));
		}
	}
	/*
	 * =============================================================================
	 * ========================== Calcular* Venda ==================================
	 * =============================================================================
	 */

	public void finalizar() {
		try {
			venda.setHorario(new Date());
			venda.setCliente(null);
			venda.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao finalizar a venda");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if (venda.getPrecoTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);

			novo();

			Messages.addGlobalInfo("Venda Salva com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar uma venda");
			erro.printStackTrace();
		}
	}

}