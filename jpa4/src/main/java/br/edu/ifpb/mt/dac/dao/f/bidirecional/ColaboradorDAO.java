package br.edu.ifpb.mt.dac.dao.f.bidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.f.bidirecional.Colaborador;

public class ColaboradorDAO extends DAO {

	public void save(Colaborador colaborador) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(colaborador);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Colaborador update(Colaborador colaborador) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Colaborador resultado = colaborador;
		try {
			resultado = em.merge(colaborador);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Colaborador colaborador) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			colaborador = em.find(Colaborador.class, colaborador.getId());
			em.remove(colaborador);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Colaborador getByID(Long idColaborador) {
		EntityManager em = getEntityManager();
		Colaborador resultado = null;
		try {
			resultado = em.find(Colaborador.class, idColaborador);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Colaborador> getAll() {
		EntityManager em = getEntityManager();
		List<Colaborador> resultado = null;
		try {
			TypedQuery<Colaborador> query = em.createQuery("SELECT c FROM COLABORADOR_BI c", Colaborador.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}