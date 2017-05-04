package br.edu.ifpb.mt.daca.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import br.edu.ifpb.mt.daca.entities.User;

public class UserDAO {
	
	private static Map<Integer, User> REPOSITORY = new ConcurrentHashMap<Integer, User>(new HashMap<Integer, User>());
	
	private static AtomicInteger ID = new AtomicInteger();
	
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
	
	public List<User> findUserByFirstName(String firstName) {
		
		if (firstName == null || firstName.trim().isEmpty()) {
			return new ArrayList<>(REPOSITORY.values());
		}
		
		List<User> resultado = new ArrayList<>();
		
		for (User user : REPOSITORY.values()) {
			if (user.getFirstName().toUpperCase().contains(firstName.toUpperCase())) {
				resultado.add(user);
			}
		}
		
		return resultado;
		
	}
}
