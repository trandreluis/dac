package br.edu.ifpb.mt.daca.dao.tablePerConcreteClass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.tablePerConcreteClass.Empregado;

public class EmpregadoDAO extends DAO {

	public void save(Empregado empregado) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(empregado);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o empregado.", pe);
		} finally {
			em.close();
		}
	}

	public Empregado update(Empregado empregado) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Empregado resultado = empregado;
		try {
			resultado = em.merge(empregado);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o empregado.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Empregado empregado) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			empregado = em.find(Empregado.class, empregado.getId());
			em.remove(empregado);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o empregado.", pe);
		} finally {
			em.close();
		}
	}

	public Empregado getByID(int empregadoID) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Empregado resultado = null;
		try {
			resultado = em.find(Empregado.class, empregadoID);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o empregado com base no ID.",
					pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Empregado> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Empregado> resultado = null;
		try {
			TypedQuery<Empregado> query = em.createQuery("SELECT e FROM Empregado e", Empregado.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os empregados.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}