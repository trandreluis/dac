package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.enumeration.CreditCard;

public class CreditCardDAO extends DAO {

	public void save(CreditCard creditCard) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(creditCard);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o cartão de crédito.", pe);
		} finally {
			em.close();
		}
	}

	public CreditCard update(CreditCard creditCard) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		CreditCard resultado = creditCard;
		try {
			resultado = em.merge(creditCard);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o cartão de crédito.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(CreditCard creditCard) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			creditCard = em.find(CreditCard.class, creditCard.getId());
			em.remove(creditCard);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o cartão de crédito.", pe);
		} finally {
			em.close();
		}
	}

	public CreditCard getByID(Long creditCardId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		CreditCard resultado = null;
		try {
			resultado = em.find(CreditCard.class, creditCardId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o cartão de crédito com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<CreditCard> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<CreditCard> resultado = null;
		try {
			TypedQuery<CreditCard> query = em.createQuery("SELECT c FROM CreditCard c", CreditCard.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os cartões de crédito.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
