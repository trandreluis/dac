package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.DogDAO;
import br.edu.ifpb.mt.daca.dao.PersonDAO;
import br.edu.ifpb.mt.daca.entities.Dog;
import br.edu.ifpb.mt.daca.entities.Person;

public class MainDeleteAll {

	public static void main(String[] args) throws DacaException {
		PersonDAO dao = new PersonDAO();
		DogDAO dogDao = new DogDAO();
		try {
			List<Person> persons = dao.getAll();
			for (Person person : persons) {
				dao.delete(person);
			}
			
			List<Dog> dogs = dogDao.getAll();
			for (Dog dog : dogs) {
				dogDao.delete(dog);
			}
		} finally {
			dao.close();
		}
	}

}