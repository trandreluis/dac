package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.st.AddressST;


public class AddressSTDAO extends DAO {
	
	public void save(AddressST addressST) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(addressST);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o endereço.", pe);
		} finally {
			em.close();
		}
	}

	public AddressST update(AddressST addressST) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		AddressST resultado = addressST;
		try {
			resultado = em.merge(addressST);
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

	public void delete(AddressST addressST) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			addressST = em.find(AddressST.class, addressST.getId());
			em.remove(addressST);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o endereço.", pe);
		} finally {
			em.close();
		}
	}

	public AddressST getByID(long addressStId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		AddressST resultado = null;
		try {
			resultado = em.find(AddressST.class, addressStId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o endereço com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<AddressST> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<AddressST> resultado = null;
		try {
			TypedQuery<AddressST> query = em.createQuery("SELECT u FROM AddressST u", AddressST.class);
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
