package br.com.julio.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.julio.drogaria.dao.ProdutoDAO;
import br.com.julio.drogaria.domain.ItemVenda;
import br.com.julio.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;

	public List<Produto> getProdutos() {
		return produtos;
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

	/*
	 * =======================================================
	 * Listar produtos na dataTable
	 * ======================================================*/
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}
	/*
	 * =======================================================
	 * Adicionar produtos na dataTable
	 * ======================================================*/
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

	}
	/*
	 * =======================================================
	 * Remover todos os produtos na dataTable
	 * ======================================================*/
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("botaoSelecionado");

		int achou = -1;

		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getProdutos().equals(itemVenda.getProdutos())) {
				achou = i;
			}
		}

		itensVenda.remove(achou);
	}
	/*
	 * =======================================================
	 * Remover item produtos na dataTable
	 * ======================================================*/
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
	}
}