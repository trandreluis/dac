package br.edu.ifpb.mt.dac.dao.e;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.e.Funcionario;

public class FuncionarioDAO extends DAO {

	public void save(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Funcionario update(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Funcionario resultado = funcionario;
		try {
			resultado = em.merge(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Funcionario funcionario) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			funcionario = em.find(Funcionario.class, funcionario.getId());
			em.remove(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Funcionario getByID(Long idFuncionario) {
		EntityManager em = getEntityManager();
		Funcionario resultado = null;
		try {
			resultado = em.find(Funcionario.class, idFuncionario);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Funcionario> getAll() {
		EntityManager em = getEntityManager();
		List<Funcionario> resultado = null;
		try {
			TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM FUNCIONARIO_UNI f", Funcionario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}