package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.daca.entities.Dog;

public class DogCriteriaDAO extends DAO {

	public void save(Dog dog) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(dog);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o cachorro.", pe);
		} finally {
			em.close();
		}
	}

	public Dog update(Dog dog) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Dog resultado = dog;
		try {
			resultado = em.merge(dog);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o cachorro.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Dog dog) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			dog = em.find(Dog.class, dog.getId());
			em.remove(dog);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o cachorro.", pe);
		} finally {
			em.close();
		}
	}

	public Dog getByID(Long dogId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Dog resultado = null;
		try {
			resultado = em.find(Dog.class, dogId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o cachorro com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Dog> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Dog> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Dog> query = cb.createQuery(Dog.class);
			Root<Dog> fromDog = query.from(Dog.class);
			query.select(fromDog);

			TypedQuery<Dog> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que vai retornar todos os cachorros numa determinada ordem.
	 * 
	 * @return
	 */
	public List<Dog> listAllDogsOrderingByWeight() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Dog> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Dog> query = cb.createQuery(Dog.class);
			Root<Dog> fromDog = query.from(Dog.class);
			query.select(fromDog);
			query.orderBy(cb.desc(fromDog.<Double>get("weight")));

			TypedQuery<Dog> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar os cachorros ordenados pelo peso.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna o menor e maior peso dos cachorros. Esta consulta faz uso da função MIN e MAX.
	 * 
	 * @return
	 */
	public List<Object[]> getDogMinAndMaxWeight() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
			Root<Dog> fromDog = query.from(Dog.class);
			Expression<Double> selection1 = cb.min(fromDog.<Double>get("weight"));
			Expression<Double> selection2 = cb.max(fromDog.<Double>get("weight"));
			query.multiselect(selection1, selection2);

			TypedQuery<Object[]> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o menor e maior peso dos cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
