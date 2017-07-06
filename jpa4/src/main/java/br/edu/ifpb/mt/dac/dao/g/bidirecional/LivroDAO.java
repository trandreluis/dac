package br.edu.ifpb.mt.dac.dao.g.bidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.g.bidirecional.Livro;

public class LivroDAO extends DAO {

	public void save(Livro livro) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(livro);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Livro update(Livro livro) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Livro resultado = livro;
		try {
			resultado = em.merge(livro);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Livro livro) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			livro = em.find(Livro.class, livro.getId());
			em.remove(livro);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Livro getByID(Long idLivro) {
		EntityManager em = getEntityManager();
		Livro resultado = null;
		try {
			resultado = em.find(Livro.class, idLivro);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Livro> getAll() {
		EntityManager em = getEntityManager();
		List<Livro> resultado = null;
		try {
			TypedQuery<Livro> query = em.createQuery("SELECT c FROM LIVRO_BI c", Livro.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}