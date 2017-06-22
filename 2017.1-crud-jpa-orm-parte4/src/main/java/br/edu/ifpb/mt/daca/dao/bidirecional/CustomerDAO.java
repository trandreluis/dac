package br.edu.ifpb.mt.daca.dao.bidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.bidirecional.Customer;

public class CustomerDAO extends DAO {

	public void save(Customer customer) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(customer);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o cliente.", pe);
		} finally {
			em.close();
		}
	}

	public Customer update(Customer customer) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Customer resultado = customer;
		try {
			resultado = em.merge(customer);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o cliente.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Customer customer) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			customer = em.find(Customer.class, customer.getId());
			em.remove(customer);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o cliente.", pe);
		} finally {
			em.close();
		}
	}

	public Customer getByID(Long customerId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Customer resultado = null;
		try {
			resultado = em.find(Customer.class, customerId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o cliente com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Customer> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Customer> resultado = null;
		try {
			TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer_BI c", Customer.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os clientes.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
