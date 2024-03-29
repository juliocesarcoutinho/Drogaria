package br.com.julio.drogaria.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.julio.drogaria.dao.FabricanteDAO;
import br.com.julio.drogaria.dao.ProdutoDAO;
import br.com.julio.drogaria.domain.Fabricante;
import br.com.julio.drogaria.domain.Produto;
import br.com.julio.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	private StreamedContent foto;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	@PostConstruct
	public void listar() {
		try {

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar Produtos");
			erro.printStackTrace();
			// TODO: handle exception
		}
	}

	public void novo() {
		try {
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("C:/Eclipse/Uploads/" + produto.getCodigo() + ".JPG");
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			
			if(produto.getCaminho()== null) {
				Messages.addGlobalError("Campo Foto é Obrigatório");
				return;
			}
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C:/Workspace/Projetos/Imagens/Produtos/" + produtoRetorno.getCodigo() + ".JPG");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			Path arquivo = Paths.get("C:/Eclipse/Uploads/" + produto.getCodigo() + ".JPG");

			Files.deleteIfExists(arquivo);

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto exluido com Sucesso");

		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Erro ao excluir o Produto");
			erro.printStackTrace();
		}
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());

			Messages.addGlobalInfo("Arquivo enviado com Sucesso");

		} catch (IOException erro) {
			Messages.addGlobalError("Erro ao fazer upload da imagem");
			erro.printStackTrace();
		}
	}
	
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			
			String prodDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante.descricao");
			
			String caminho = Faces.getRealPath("/reports/produtos.jasper");
			

			Map<String, Object> parametros = new HashMap<>();
			if(prodDescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			}else {
				parametros.put("PRODUTO_DESCRICAO", "%" + prodDescricao + "%");
			}
			if(fabDescricao == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			}else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + fabDescricao + "%");
			}

			Connection conexao = HibernateUtil.novaConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	public void download(ActionEvent evento) {
		try {
			
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			InputStream stream = new FileInputStream("C:/Eclipse/Uploads/" + produto.getCodigo() + ".JPG");
			foto = new DefaultStreamedContent(stream, "image/JPG", + produto.getCodigo() + ".JPG");
		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Erro ao Fazer o download da foto");
		}
		
	}
}
