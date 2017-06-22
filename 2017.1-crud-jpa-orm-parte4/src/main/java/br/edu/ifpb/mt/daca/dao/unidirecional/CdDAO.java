package br.edu.ifpb.mt.daca.dao.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.unidirecional.CD;

public class CdDAO extends DAO {

	public void save(CD cd) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(cd);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o CD.", pe);
		} finally {
			em.close();
		}
	}

	public CD update(CD cd) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		CD resultado = cd;
		try {
			resultado = em.merge(cd);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o CD.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(CD cd) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			cd = em.find(CD.class, cd.getId());
			em.remove(cd);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o CD.", pe);
		} finally {
			em.close();
		}
	}

	public CD getByID(Long cdId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		CD resultado = null;
		try {
			resultado = em.find(CD.class, cdId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o CD com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<CD> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<CD> resultado = null;
		try {
			TypedQuery<CD> query = em.createQuery("SELECT cd FROM CD_UNI cd", CD.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os CDs.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}
