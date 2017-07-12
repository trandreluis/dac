package br.edu.ifpb.mt.daca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAO {

	static EntityManagerFactory emf;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("daca");
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close() {
		emf.close();
	}
}
