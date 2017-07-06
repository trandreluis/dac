package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.mt.daca.entities.Address;
import br.edu.ifpb.mt.daca.entities.Dog;
import br.edu.ifpb.mt.daca.entities.Person;

public class PersonCriteriaDAO extends DAO {

	public void save(Person person) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(person);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a pessoa.", pe);
		} finally {
			em.close();
		}
	}

	public Person update(Person person) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Person resultado = person;
		try {
			resultado = em.merge(person);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar a pessoa.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Person person) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			person = em.find(Person.class, person.getId());
			em.remove(person);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a pessoa.", pe);
		} finally {
			em.close();
		}
	}

	public Person getByID(Long personId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Person resultado = null;
		try {
			resultado = em.find(Person.class, personId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a pessoa com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Person> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Person> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Person> query = cb.createQuery(Person.class);
			Root<Person> fromPerson = query.from(Person.class);
			query.select(fromPerson);

			TypedQuery<Person> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todas as pessoas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que recebe um tipo simples como parâmetro.
	 * 
	 * @param name
	 * @return
	 */
	public Person findPersonByName(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Person resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Person> query = cb.createQuery(Person.class);
			Root<Person> fromPerson = query.from(Person.class);
			query.select(fromPerson);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), name);
			query.where(condition);

			TypedQuery<Person> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a pessoa pelo nome.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que recebe um objeto como parâmetro.
	 * 
	 * @param address
	 * @return
	 */
	public Person findPersonByAddressObject(Address address) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Person resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Person> query = cb.createQuery(Person.class);
			Root<Person> fromPerson = query.from(Person.class);
			query.select(fromPerson);

			Predicate condition = cb.equal(fromPerson.<Address>get("address"), address);
			query.where(condition);

			TypedQuery<Person> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a pessoa pelo endereço.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna apenas um atributo em vez de uma entidade.
	 * 
	 * @param name
	 * @return
	 */
	public String findAddressNameOfPerson(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		String resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<String> query = cb.createQuery(String.class);
			Root<Person> fromPerson = query.from(Person.class);
			Path<String> selection = fromPerson.<Address>get("address").<String>get("streetName");
			query.select(selection);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), name);
			query.where(condition);

			TypedQuery<String> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a rua de uma pessoa com determinado nome.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que vai fazer fetch de um relacionamento lazy. Cuidado! Na forma como está escrita esta consulta, apenas
	 * pessoas que tiverem cachorros serão retornadas.
	 * 
	 * @param name
	 * @return
	 */
	public Person findPersonByNameWithAllDogs(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Person resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Person> query = cb.createQuery(Person.class);
			Root<Person> fromPerson = query.from(Person.class);
			fromPerson.fetch("dogs"); // Retorna um objeto do tipo "Fetch<Person, Dog>"
			query.select(fromPerson).distinct(true);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), name);
			query.where(condition);

			TypedQuery<Person> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar uma pessoa (com todos seus cachorros) pelo nome.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que vai fazer fetch de um relacionamento lazy. Cuidado! Na forma como está escrita esta consulta,
	 * pessoas que *NÃO* tiverem cachorros serão retornadas também.
	 * 
	 * @param name
	 * @return
	 */
	public Person findPersonByNameThatMayNotHaveDogs(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Person resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Person> query = cb.createQuery(Person.class);
			Root<Person> fromPerson = query.from(Person.class);
			fromPerson.fetch("dogs", JoinType.LEFT); // Retorna um objeto do tipo "Fetch<Person, Dog>"
			query.select(fromPerson);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), name);
			query.where(condition);

			TypedQuery<Person> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar uma pessoa pelo nome, incluindo as que não tiverem cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna a média de idade das pessoas. Esta consulta faz uso da função AVG.
	 * 
	 * @return
	 */
	public Double getPersonsAgeAverage() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Double resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Double> query = cb.createQuery(Double.class);
			Root<Person> fromPerson = query.from(Person.class);
			Expression<Double> selection = cb.avg(fromPerson.<Double>get("age"));
			query.select(selection);

			TypedQuery<Double> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a média de idade das pessoas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna as pessoas que tem cachorro com peso maior que o valor passado como parâmetro. Esta consulta
	 * faz uso da função COUNT.
	 * 
	 * @param weight
	 * @return
	 */
	public List<Object[]> getPersonsWithDogsWeightHigherThan(double weight) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
			Root<Person> fromPerson = query.from(Person.class);

			query.multiselect(fromPerson.<String>get("name"), cb.count(fromPerson));

			Join<Person, Dog> joinDogs = fromPerson.join("dogs");
			Predicate condition = cb.gt(joinDogs.<Double>get("weight"), weight);
			query.where(condition);

			query.groupBy(fromPerson.<Long>get("id"), fromPerson.<String>get("name"));

			TypedQuery<Object[]> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar as pessoas que tem cachorro com peso maior que o valor passado como parâmetro.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna a soma da idade de todas as pessoas. Esta consulta faz uso da função SUM.
	 * 
	 * @return
	 */
	public Long getTheSumOfAllAges() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Long resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
			Root<Person> fromPerson = query.from(Person.class);
			Expression<Integer> selection = cb.sum(fromPerson.<Integer>get("age"));
			query.select(selection);

			TypedQuery<Integer> typedQuery = em.createQuery(query);
			resultado = new Long(typedQuery.getSingleResult());
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a soma da idade de todas as pessoas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna a pessoa cujo nome em caixa-alta seja igual ao passado como parâmetro. Esta consulta faz uso
	 * da função LOWER e UPPER.
	 * 
	 * @param name
	 * @return
	 */
	public String getLoweredCaseNameFromUpperCase(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		String resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<String> query = cb.createQuery(String.class);
			Root<Person> fromPerson = query.from(Person.class);
			Expression<String> selection = cb.lower(fromPerson.<String>get("name"));
			query.select(selection);

			Predicate condition = cb.equal(cb.upper(fromPerson.<String>get("name")), name.toUpperCase());
			query.where(condition);

			TypedQuery<String> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a pessoa cujo nome em caixa-alta seja igual ao passado como parâmetro.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna a idade de uma dada pessoa MOD um dado número. Esta consulta faz uso da função MOD.
	 * 
	 * @param personName
	 * @param modBy
	 * @return
	 */
	public Integer getPersonAgeMod(String personName, int modBy) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Integer resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
			Root<Person> fromPerson = query.from(Person.class);
			Expression<Integer> selection = cb.mod(fromPerson.<Integer>get("age"), modBy);
			query.select(selection);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), personName);
			query.where(condition);

			TypedQuery<Integer> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a idade de uma dada pessoa MOD um dado número.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna a raiz quadrada da idade de uma dada pessoa. Esta consulta faz uso da função TRIM e SQRT.
	 * 
	 * @param name
	 * @return
	 */
	public Double getPersonAgeSqrtUsingTrim(String name) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Double resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Double> query = cb.createQuery(Double.class);
			Root<Person> fromPerson = query.from(Person.class);
			Expression<Double> selection = cb.sqrt(fromPerson.<Integer>get("age"));
			query.select(selection);

			Predicate condition = cb.equal(fromPerson.<String>get("name"), name.trim());
			query.where(condition);

			TypedQuery<Double> typedQuery = em.createQuery(query);
			resultado = typedQuery.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a raiz quadrada da idade de uma dada pessoa.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	/**
	 * Consulta que retorna as pessoas que tem mais que uma determinada quantidade de cachorros. Esta consulta faz uso
	 * da função HAVING e COUNT.
	 * 
	 * @param dogAmount
	 * @return
	 */
	public List<Object[]> getPersonByHavingDogAmountHigherThan(long dogAmount) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
			Root<Person> fromPerson = query.from(Person.class);

			query.multiselect(fromPerson.<String>get("name"), cb.count(fromPerson));

			Join<Person, Dog> joinDogs = fromPerson.join("dogs");

			query.groupBy(fromPerson.<Long>get("id"), fromPerson.<String>get("name"));
			
			query.having(cb.gt(cb.count(joinDogs), dogAmount));

			TypedQuery<Object[]> typedQuery = em.createQuery(query);
			resultado = typedQuery.getResultList();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar as pessoas que tem mais que uma determinada quantidade de cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
