package br.edu.ifpb.mt.dac.dao.a.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.a.unidirecional.Turista;

public class TuristaDAO extends DAO {

	public void save(Turista turista) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(turista);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Turista update(Turista turista) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Turista resultado = turista;
		try {
			resultado = em.merge(turista);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Turista turista) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			turista = em.find(Turista.class, turista.getId());
			em.remove(turista);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Turista getByID(Long artistId) {
		EntityManager em = getEntityManager();
		Turista resultado = null;
		try {
			resultado = em.find(Turista.class, artistId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Turista> getAll() {
		EntityManager em = getEntityManager();
		List<Turista> resultado = null;
		try {
			TypedQuery<Turista> query = em.createQuery("SELECT t FROM TURISTA_UNI t", Turista.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}