package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.User;

public class UserDAO extends DAO {

	public void save(User user) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(user);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		} finally {
			em.close();
		}
	}

	public User update(User user) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		User resultado = user;
		try {
			resultado = em.merge(user);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(User user) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			user = em.find(User.class, user.getId());
			em.remove(user);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		} finally {
			em.close();
		}
	}

	public User getByID(int userId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		User resultado = null;
		try {
			resultado = em.find(User.class, userId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<User> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<User> resultado = null;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os usuários.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public List<User> findUserByFirstName(String firstName) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<User> resultado = null;
		if (firstName == null) {
			firstName = "";
		}
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.firstName LIKE :firstName",
					User.class);
			query.setParameter("firstName", "%" + firstName + "%");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os usuários "
					+ "que tem um determinado primeiro nome.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}
