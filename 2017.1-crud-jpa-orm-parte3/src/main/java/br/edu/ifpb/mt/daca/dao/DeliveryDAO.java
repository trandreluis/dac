package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.Delivery;

public class DeliveryDAO extends DAO {
	
	public void save(Delivery delivery) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(delivery);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a entrega.", pe);
		} finally {
			em.close();
		}
	}

	public Delivery update(Delivery delivery) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Delivery resultado = delivery;
		try {
			resultado = em.merge(delivery);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar a entrega.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Delivery delivery) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			delivery = em.find(Delivery.class, delivery.getId());
			em.remove(delivery);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a entrega.", pe);
		} finally {
			em.close();
		}
	}

	public Delivery getByID(long deliveryId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Delivery resultado = null;
		try {
			resultado = em.find(Delivery.class, deliveryId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a entrega com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Delivery> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Delivery> resultado = null;
		try {
			TypedQuery<Delivery> query = em.createQuery("SELECT delivery FROM Delivery delivery", Delivery.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todas as entregas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
