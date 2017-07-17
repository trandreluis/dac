package br.edu.ifpb.mt.daca.dao.joinedSubclass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.joinedSubclass.Veiculo;

public class VeiculoDAO extends DAO {

	public void save(Veiculo veiculo) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(veiculo);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o veiculo.", pe);
		} finally {
			em.close();
		}
	}

	public Veiculo update(Veiculo veiculo) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Veiculo resultado = veiculo;
		try {
			resultado = em.merge(veiculo);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o veiculo.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Veiculo veiculo) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			veiculo = em.find(Veiculo.class, veiculo.getId());
			em.remove(veiculo);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o veiculo.", pe);
		} finally {
			em.close();
		}
	}

	public Veiculo getByID(int veiculoID) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Veiculo resultado = null;
		try {
			resultado = em.find(Veiculo.class, veiculoID);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o veiculo com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Veiculo> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Veiculo> resultado = null;
		try {
			TypedQuery<Veiculo> query = em.createQuery("SELECT p FROM Projeto p", Veiculo.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os veiculos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}