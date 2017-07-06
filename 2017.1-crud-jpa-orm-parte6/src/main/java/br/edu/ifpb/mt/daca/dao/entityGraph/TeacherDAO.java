package br.edu.ifpb.mt.daca.dao.entityGraph;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.entityGraph.Teacher;

public class TeacherDAO extends DAO {

	public void save(Teacher teacher) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(teacher);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o professor.", pe);
		} finally {
			em.close();
		}
	}

	public Teacher update(Teacher teacher) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Teacher resultado = teacher;
		try {
			resultado = em.merge(teacher);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o professor.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Teacher teacher) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			teacher = em.find(Teacher.class, teacher.getId());
			em.remove(teacher);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o professor.", pe);
		} finally {
			em.close();
		}
	}

	public Teacher getByID(Long teacherId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Teacher resultado = null;
		try {
			resultado = em.find(Teacher.class, teacherId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o professor com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Teacher> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Teacher> resultado = null;
		try {
			TypedQuery<Teacher> query = em.createNamedQuery("Teacher.getAll", Teacher.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os professores.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public List<Teacher> getAllFetchEverything() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Teacher> resultado = null;
		try {
			// OBS: "The association referenced by the right side of the FETCH JOIN clause must be an association or
			// element collection that is referenced from an entity or embeddable that is returned as a result of the
			// query. It is not permitted to specify an identification variable for the objects referenced by the right
			// side of the FETCH JOIN clause, and hence references to the implicitly fetched entities or elements cannot
			// appear elsewhere in the query."
			// Referência: http://download.oracle.com/otndocs/jcp/persistence-2_1-fr-eval-spec/index.html
			TypedQuery<Teacher> query = em.createNamedQuery("Teacher.getAllFetchEverything", Teacher.class);

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os professores carregando todos os dados.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
	
	public List<Teacher> getAllFetchEverythingWithEntityGraph() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Teacher> resultado = null;
		try {
			
			TypedQuery<Teacher> query = em.createNamedQuery("Teacher.getAll", Teacher.class);
			
			// javax.persistence.fetchgraph:
			// "A fetch graph consists of only the fields explicitly specified in the EntityGraph instance, 
			// and ignores the default entity graph settings."
			// javax.persistence.loadgraph:
			// " A load graph consists of the fields explicitly specified in the EntityGraph instance plus 
			// any fields in the default entity graph."
			// 
			// Referência: https://docs.oracle.com/javaee/7/tutorial/persistence-entitygraphs001.htm#sthref2180
			
			@SuppressWarnings("unchecked")
			EntityGraph<Teacher> graph = (EntityGraph<Teacher>) em.getEntityGraph("graph.Teacher.everything");
			//query.setHint("javax.persistence.fetchgraph", graph);
			query.setHint("javax.persistence.loadgraph", graph);

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os professores carregando todos os dados.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}
