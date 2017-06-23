package br.edu.ifpb.mt.dac.dao.d.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.dao.generic.DAO;
import br.edu.ifpb.mt.dac.entidades.d.unidirecional.Postagem;

public class PostagemDAO extends DAO {

	public void save(Postagem postagem) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(postagem);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Postagem update(Postagem postagem) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Postagem resultado = postagem;
		try {
			resultado = em.merge(postagem);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Postagem postagem) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			postagem = em.find(Postagem.class, postagem.getId());
			em.remove(postagem);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public Postagem getByID(Long idPostagem) {
		EntityManager em = getEntityManager();
		Postagem resultado = null;
		try {
			resultado = em.find(Postagem.class, idPostagem);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Postagem> getAll() {
		EntityManager em = getEntityManager();
		List<Postagem> resultado = null;
		try {
			TypedQuery<Postagem> query = em.createQuery("SELECT c FROM POSTAGEM_UNI c", Postagem.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		return resultado;
	}

}