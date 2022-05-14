package br.com.julio.drogaria.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.julio.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	@SuppressWarnings({ })
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			 // Create CriteriaBuilder
	           CriteriaBuilder builder = sessao.getCriteriaBuilder();
	           
	           // Create CriteriaQuery
	            CriteriaQuery<Entidade> consulta = builder.createQuery(classe);
	    
	           // Specify criteria root
	           consulta.from(classe);
	    
	           // Execute query
	           List<Entidade> resultado = sessao.createQuery(consulta).getResultList();
	    
	   return resultado;
		} catch(RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
}
