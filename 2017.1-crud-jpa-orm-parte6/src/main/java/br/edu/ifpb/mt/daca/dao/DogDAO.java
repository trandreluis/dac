package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.Dog;

public class DogDAO extends DAO {

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
			TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d", Dog.class);
			resultado = query.getResultList();
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
			TypedQuery<Dog> query = em.createQuery("SELECT d FROM Dog d ORDER BY d.weight DESC", Dog.class);
			resultado = query.getResultList();
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
	@SuppressWarnings("unchecked")
	public List<Object[]> getDogMinAndMaxWeight() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em.createQuery("SELECT MIN(d.weight), MAX(d.weight) FROM Dog d");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o menor e maior peso dos cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
