package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.Address;
import br.edu.ifpb.mt.daca.entities.Person;

public class PersonDAO extends DAO {

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
			TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
			resultado = query.getResultList();
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
			TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);
			query.setParameter("name", name);
			resultado = query.getSingleResult();
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
			TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address = :address", Person.class);
			query.setParameter("address", address);
			resultado = query.getSingleResult();
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
			TypedQuery<String> query = em.createQuery("SELECT p.address.streetName FROM Person p WHERE p.name = :name",
					String.class);
			query.setParameter("name", name);
			resultado = query.getSingleResult();
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
			TypedQuery<Person> query = em.createQuery("SELECT DISTINCT p FROM Person p JOIN FETCH p.dogs WHERE p.name = :name",
					Person.class);
			query.setParameter("name", name);
			resultado = query.getSingleResult();
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
			TypedQuery<Person> query = em.createQuery(
					"SELECT p FROM Person p LEFT JOIN FETCH p.dogs WHERE p.name = :name", Person.class);
			query.setParameter("name", name);
			resultado = query.getSingleResult();
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
			TypedQuery<Double> query = em.createQuery("SELECT AVG(p.age) FROM Person p", Double.class);
			resultado = query.getSingleResult();
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
	@SuppressWarnings("unchecked")
	public List<Object[]> getPersonsWithDogsWeightHigherThan(double weight) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em
					.createQuery("SELECT p.name, COUNT(p) FROM Person p JOIN p.dogs d WHERE d.weight > :weight GROUP BY p.id, p.name");
			query.setParameter("weight", weight);
			resultado = query.getResultList();
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
			TypedQuery<Long> query = em.createQuery("SELECT SUM(p.age) FROM Person p", Long.class);
			resultado = query.getSingleResult();
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
			TypedQuery<String> query = em.createQuery("SELECT LOWER(p.name) FROM Person p WHERE UPPER(p.name) = :name",
					String.class);
			query.setParameter("name", name.toUpperCase());
			resultado = query.getSingleResult();
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
			TypedQuery<Integer> query = em.createQuery("SELECT MOD(p.age, :modBy) FROM Person p WHERE p.name = :name",
					Integer.class);
			query.setParameter("modBy", modBy);
			query.setParameter("name", personName);
			resultado = query.getSingleResult();
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
			TypedQuery<Double> query = em.createQuery("SELECT SQRT(p.age) FROM Person p WHERE p.name = TRIM(:name)",
					Double.class);
			query.setParameter("name", name);
			resultado = query.getSingleResult();
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
	@SuppressWarnings("unchecked")
	public List<Object[]> getPersonByHavingDogAmountHigherThan(long dogAmount) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em
					.createQuery("SELECT p.name, COUNT(p) FROM Person p JOIN p.dogs d GROUP BY p.id, p.name HAVING COUNT(d) > :dogAmount");
			query.setParameter("dogAmount", dogAmount);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar as pessoas que tem mais que uma determinada quantidade de cachorros.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
