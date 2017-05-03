package br.edu.ifpb.mt.daca.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import br.edu.ifpb.mt.daca.entities.User;
import br.edu.ifpb.mt.daca.util.Util;

public class UserDAO {
	private static Map<Integer, User> REPOSITORY = new ConcurrentHashMap<Integer, User>(new HashMap<Integer, User>());
	
	private static AtomicInteger ID = new AtomicInteger();
	
	static {
		
		int cId = ID.getAndIncrement();
		REPOSITORY.put(cId, new User(cId, "Pedro", "Melo Costa", "pedroMeloCosta@ggmail.com", 
				Util.getDate(1999, Calendar.JANUARY, 1)));
		cId = ID.getAndIncrement();
		REPOSITORY.put(cId, new User(cId, "Vitor", "Gomes Cavalcanti", "vitorGomesCavalcanti@ggmail.com", 
				Util.getDate(2000, Calendar.FEBRUARY, 2)));
		cId = ID.getAndIncrement();
		REPOSITORY.put(cId, new User(cId, "Emilly", "Almeida Dias", "emillyAlmeidaDias@ggmail.com", 
				Util.getDate(2001, Calendar.MARCH, 3)));
		cId = ID.getAndIncrement();
		REPOSITORY.put(cId, new User(cId, "Gabriela", "Barros Santos", "gabrielaBarrosSantos@ggmail.com", 
				Util.getDate(2002, Calendar.APRIL, 4)));
		cId = ID.getAndIncrement();
		REPOSITORY.put(cId, new User(cId, "Leonardo", "Sousa Correia", "leonardoSousaCorreia@ggmail.com", 
				Util.getDate(2002, Calendar.MAY, 5)));
	}
	
	public void save(User user) {
		user.setId(ID.getAndIncrement());
		REPOSITORY.put(user.getId(), user);
	}

	public void update(User user) {
		REPOSITORY.put(user.getId(), user);
	}

	public void delete(User user) {
		REPOSITORY.remove(user.getId());
	}

	public User getByID(int userId) {
		return REPOSITORY.get(userId);
	}

	public List<User> getAll() {
		return new ArrayList<User>(REPOSITORY.values());
	}

}
