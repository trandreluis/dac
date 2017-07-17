package br.edu.ifpb.mt.daca.dao.singleTablePerClassHierarchy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy.Projeto;

public class ProjetoDAO extends DAO {

	public void save(Projeto projeto) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(projeto);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o projeto.", pe);
		} finally {
			em.close();
		}
	}

	public Projeto update(Projeto projeto) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Projeto resultado = projeto;
		try {
			resultado = em.merge(projeto);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o projeto.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Projeto projeto) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			projeto = em.find(Projeto.class, projeto.getId());
			em.remove(projeto);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o projeto.", pe);
		} finally {
			em.close();
		}
	}

	public Projeto getByID(int projetoID) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Projeto resultado = null;
		try {
			resultado = em.find(Projeto.class, projetoID);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o projeto com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Projeto> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Projeto> resultado = null;
		try {
			TypedQuery<Projeto> query = em.createQuery("SELECT p FROM Projeto p", Projeto.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os projetos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
	
}