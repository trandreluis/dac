package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.embeddedid.NewsEI;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsIdEI;

public class NewsEIDAO extends DAO {

	public void save(NewsEI newsEI) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(newsEI);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a notícia.", pe);
		} finally {
			em.close();
		}
	}

	public NewsEI update(NewsEI newsEI) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		NewsEI resultado = newsEI;
		try {
			resultado = em.merge(newsEI);
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

	public void delete(NewsEI newsEI) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			newsEI = em.find(NewsEI.class, newsEI.getId());
			em.remove(newsEI);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a notícia.", pe);
		} finally {
			em.close();
		}
	}

	public NewsEI getByID(NewsIdEI newsIdEI) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		NewsEI resultado = null;
		try {
			resultado = em.find(NewsEI.class, newsIdEI);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a notícia com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<NewsEI> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<NewsEI> resultado = null;
		try {
			TypedQuery<NewsEI> query = em.createQuery("SELECT u FROM NewsEI u", NewsEI.class);
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
