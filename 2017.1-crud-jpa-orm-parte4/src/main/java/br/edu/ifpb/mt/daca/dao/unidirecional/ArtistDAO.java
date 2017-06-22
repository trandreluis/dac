package br.edu.ifpb.mt.daca.dao.unidirecional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.daca.dao.DAO;
import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.entities.unidirecional.Artist;

public class ArtistDAO extends DAO {

	public void save(Artist artist) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(artist);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar salvar o artista.", pe);
		} finally {
			em.close();
		}
	}

	public Artist update(Artist artist) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Artist resultado = artist;
		try {
			resultado = em.merge(artist);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar atualizar o artista.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Artist artist) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			artist = em.find(Artist.class, artist.getId());
			em.remove(artist);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar remover o artista.", pe);
		} finally {
			em.close();
		}
	}

	public Artist getByID(Long artistId) throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		Artist resultado = null;
		try {
			resultado = em.find(Artist.class, artistId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar o artista com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Artist> getAll() throws PersistenciaDacaException {
		EntityManager em = getEntityManager();
		List<Artist> resultado = null;
		try {
			TypedQuery<Artist> query = em.createQuery("SELECT a FROM Artist_UNI a", Artist.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacaException("Ocorreu algum erro ao tentar recuperar todos os artistas.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
