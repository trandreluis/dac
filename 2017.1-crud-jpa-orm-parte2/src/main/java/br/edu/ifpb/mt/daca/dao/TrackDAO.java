package br.edu.ifpb.mt.daca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.entities.Track;


public class TrackDAO extends DAO {

	public void save(Track track) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(track);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar a faixa.", pe);
		} finally {
			em.close();
		}
	}

	public Track update(Track track) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Track resultado = track;
		try {
			resultado = em.merge(track);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar a faixa.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Track track) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			track = em.find(Track.class, track.getId());
			em.remove(track);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover a faixa.", pe);
		} finally {
			em.close();
		}
	}

	public Track getByID(long id) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Track resultado = null;
		try {
			resultado = em.find(Track.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar a faixa com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Track> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Track> resultado = null;
		try {
			TypedQuery<Track> query = em.createQuery("SELECT t FROM Track t", Track.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todas as faixas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
