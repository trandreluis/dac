package br.edu.ifpb.mt.daca;

import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON02_NAME;
import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON03_NAME;
import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON04_NAME;
import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON06_NAME;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.DogCriteriaDAO;
import br.edu.ifpb.mt.daca.dao.PersonCriteriaDAO;
import br.edu.ifpb.mt.daca.entities.Dog;
import br.edu.ifpb.mt.daca.entities.Person;

public class MainSimpleQueriesCriteria {

	public static void main(String[] args) throws DacaException {

		PersonCriteriaDAO personDAO = new PersonCriteriaDAO();
		DogCriteriaDAO dogDAO = new DogCriteriaDAO();

		try {
			System.out.println("### getAll:");
			List<Dog> dogs = dogDAO.getAll();

			for (Dog dog : dogs) {
				System.out.println(dog.getName());
			}

			System.out.println("### findPersonByName:");
			Person person03 = personDAO.findPersonByName(PERSON03_NAME);
			System.out.println(person03.getName());


			System.out.println("### findPersonByAddressObject:");
			Person person01 = new Person();
			person01.setAddress(person03.getAddress());
			Person savedPerson = personDAO.findPersonByAddressObject(person01.getAddress());
			System.out.println(savedPerson.getName());

			System.out.println("### listAllDogsOrderingByWeight:");
			List<Dog> dogsByWeight = dogDAO.listAllDogsOrderingByWeight();

			for (Dog dog : dogsByWeight) {
				System.out.println(dog.getWeight());
			}

			System.out.println("### findAddressNameOfPerson:");
			String addressName = personDAO.findAddressNameOfPerson(PERSON04_NAME);
			System.out.println("Person 04 address is: " + addressName);

			System.out.println("### findPersonByNameWithAllDogs:");
			Person person02 = personDAO.findPersonByNameWithAllDogs(PERSON02_NAME);

			for (Dog dog : person02.getDogs()) {
				System.out.println("Person 02 Dog: " + dog.getName());
			}

			System.out.println("### findPersonByNameThatMayNotHaveDogs:");
			Person person05 = personDAO.findPersonByNameThatMayNotHaveDogs(PERSON06_NAME);
			System.out.println("Is the list of the Dogs from the Person 05 empty? " + person05.getDogs().size());

		} finally {
			personDAO.close();
			dogDAO.close();
		}

	}

}
