package br.edu.ifpb.mt.dac.dao.c.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.c.unidirecional.Celular;

public class CelularDAO extends DAO {

	public void save(Celular celular) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(celular);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Celular update(Celular celular) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Celular resultado = celular;
		try {
			resultado = em.merge(celular);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Celular celular) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			celular = em.find(Celular.class, celular.getId());
			em.remove(celular);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Celular getByID(Long idCelular) {
		EntityManager em = getEntityManager();
		Celular resultado = null;
		try {
			resultado = em.find(Celular.class, idCelular);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Celular> getAll() {
		EntityManager em = getEntityManager();
		List<Celular> resultado = null;
		try {
			TypedQuery<Celular> query = em.createQuery("SELECT c FROM CELULAR_UNI c", Celular.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}