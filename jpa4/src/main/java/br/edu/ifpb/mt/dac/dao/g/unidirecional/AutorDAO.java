package br.edu.ifpb.mt.dac.dao.g.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.g.unidirecional.Autor;

public class AutorDAO extends DAO {

	public void save(Autor autor) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(autor);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Autor update(Autor autor) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Autor resultado = autor;
		try {
			resultado = em.merge(autor);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Autor autor) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			autor = em.find(Autor.class, autor.getId());
			em.remove(autor);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Autor getByID(Long idAutor) {
		EntityManager em = getEntityManager();
		Autor resultado = null;
		try {
			resultado = em.find(Autor.class, idAutor);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Autor> getAll() {
		EntityManager em = getEntityManager();
		List<Autor> resultado = null;
		try {
			TypedQuery<Autor> query = em.createQuery("SELECT a FROM AUTOR_UNI a", Autor.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}