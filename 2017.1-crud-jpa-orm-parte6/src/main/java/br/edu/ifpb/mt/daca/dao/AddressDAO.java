package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.Address;


public class AddressDAO extends DAO {

	public void save(Address address) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(address);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o endereço.", pe);
		} finally {
			em.close();
		}
	}

	public Address update(Address address) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Address resultado = address;
		try {
			resultado = em.merge(address);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o endereço.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Address address) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			address = em.find(Address.class, address.getId());
			em.remove(address);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o endereço.", pe);
		} finally {
			em.close();
		}
	}

	public Address getByID(Long addressId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Address resultado = null;
		try {
			resultado = em.find(Address.class, addressId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o endereço com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Address> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Address> resultado = null;
		try {
			TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os endereços.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
