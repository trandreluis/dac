package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.idclass.NewsIC;
import br.edu.ifpb.mt.daca.entities.idclass.NewsIdIC;

public class NewsICDAO extends DAO {

	public void save(NewsIC newsIC) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(newsIC);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a notícia.", pe);
		} finally {
			em.close();
		}
	}

	public NewsIC update(NewsIC newsIC) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		NewsIC resultado = newsIC;
		try {
			resultado = em.merge(newsIC);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar a notícia.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(NewsIC newsIC) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			NewsIdIC primaryKey = new NewsIdIC();
			primaryKey.setTitle(newsIC.getTitle());
			primaryKey.setLanguage(newsIC.getLanguage());
			newsIC = em.find(NewsIC.class, primaryKey);
			em.remove(newsIC);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a notícia.", pe);
		} finally {
			em.close();
		}
	}

	public NewsIC getByID(NewsIdIC id) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		NewsIC resultado = null;
		try {
			resultado = em.find(NewsIC.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a notícia com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<NewsIC> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<NewsIC> resultado = null;
		try {
			TypedQuery<NewsIC> query = em.createQuery("SELECT u FROM NewsIC u", NewsIC.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todas as notícias.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
