package br.edu.ifpb.mt.daca.dao.bidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.bidirecional.Order;

public class OrderDAO extends DAO {

	public void save(Order order) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(order);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a encomenda.", pe);
		} finally {
			em.close();
		}
	}

	public Order update(Order order) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Order resultado = order;
		try {
			resultado = em.merge(order);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar a encomenda.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Order order) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			order = em.find(Order.class, order.getId());
			em.remove(order);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a encomenda.", pe);
		} finally {
			em.close();
		}
	}

	public Order getByID(Long orderId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Order resultado = null;
		try {
			resultado = em.find(Order.class, orderId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a encomenda com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Order> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Order> resultado = null;
		try {
			TypedQuery<Order> query = em.createQuery("SELECT o FROM Order_BI o", Order.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todas as encomendas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
