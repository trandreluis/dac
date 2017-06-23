package br.edu.ifpb.mt.dac.dao.b.bidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.b.bidirecional.Departamento;

public class DepartamentoDAO extends DAO {

	public void save(Departamento departamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(departamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Departamento update(Departamento departamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Departamento resultado = departamento;
		try {
			resultado = em.merge(departamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Departamento departamento) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			departamento = em.find(Departamento.class, departamento.getId());
			em.remove(departamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Departamento getByID(Long idDepartamento) {
		EntityManager em = getEntityManager();
		Departamento resultado = null;
		try {
			resultado = em.find(Departamento.class, idDepartamento);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Departamento> getAll() {
		EntityManager em = getEntityManager();
		List<Departamento> resultado = null;
		try {
			TypedQuery<Departamento> query = em.createQuery("SELECT d FROM DEPARTAMENTO_BI d", Departamento.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}