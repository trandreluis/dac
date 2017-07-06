package br.edu.ifpb.mt.dac.dao.f.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.f.unidirecional.Projeto;

public class ProjetoDAO extends DAO {

	public void save(Projeto projeto) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(projeto);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Projeto update(Projeto projeto) {
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
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Projeto projeto) {
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
		} finally {
			em.close();
		}
	}

	public Projeto getByID(Long idProjeto) {
		EntityManager em = getEntityManager();
		Projeto resultado = null;
		try {
			resultado = em.find(Projeto.class, idProjeto);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Projeto> getAll() {
		EntityManager em = getEntityManager();
		List<Projeto> resultado = null;
		try {
			TypedQuery<Projeto> query = em.createQuery("SELECT p FROM PROJETO_UNI p", Projeto.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}