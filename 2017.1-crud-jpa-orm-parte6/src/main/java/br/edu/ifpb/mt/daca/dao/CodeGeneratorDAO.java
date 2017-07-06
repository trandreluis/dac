package br.edu.ifpb.mt.daca.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import br.edu.ifpb.mt.daca.entities.Address;
import br.edu.ifpb.mt.daca.entities.Dog;
import br.edu.ifpb.mt.daca.entities.Person;

public class CodeGeneratorDAO extends DAO {

	public static final String PERSON01_NAME = "John";

	public static final String PERSON02_NAME = "Mary";

	public static final String PERSON03_NAME = "Anna";

	public static final String PERSON04_NAME = "Joseph";

	public static final String PERSON05_NAME = "Mark";

	public static final String PERSON06_NAME = "I will not have any relationship";

	public void generateData() {

		int year = 1995;
		int month = 1;
		int day = 10;

		Dog dog01 = new Dog("Yellow", 3.5d, createNewDate(day, month, year));
		Dog dog02 = new Dog("Brown", 8.5d, createNewDate(++day, ++month, ++year));
		Dog dog03 = new Dog("Dark", 15.5d, createNewDate(++day, ++month, ++year));
		Dog dog04 = new Dog("Kaka", 4.3d, createNewDate(++day, ++month, ++year));
		Dog dog05 = new Dog("Pepe", 8.2d, createNewDate(++day, ++month, ++year));
		Dog dog06 = new Dog("Casillas", 6.1d, createNewDate(++day, ++month, ++year));
		Dog dog07 = new Dog("Fish", 6.7d, createNewDate(++day, ++month, ++year));
		Dog dog08 = new Dog("Lion", 3.1d, createNewDate(++day, ++month, ++year));
		Dog dog09 = new Dog("Cat", 5.5d, createNewDate(++day, ++month, ++year));
		Dog dog10 = new Dog("Java", 21.7d, createNewDate(++day, ++month, ++year));
		Dog dog11 = new Dog("JSF", 23.65d, createNewDate(++day, ++month, ++year));
		Dog dog12 = new Dog("VRaptor", 24.0d, createNewDate(++day, ++month, ++year));
		Dog dog13 = new Dog("Ferrari", 3.7d, createNewDate(++day, ++month, ++year));
		Dog dog14 = new Dog("Porshe", 1.33d, createNewDate(++day, ++month, ++year));
		Dog dog15 = new Dog("Bike", 4.44d, createNewDate(++day, ++month, ++year));
		Dog dog16 = new Dog("Rambo", 5.44d, createNewDate(++day, ++month, 2015));
		Dog dog17 = new Dog("Terminator", 3.88d, createNewDate(++day, ++month, 2016));
		Dog dog18 = new Dog("John McClan", 3.88d, createNewDate(++day, ++month, 2016));

		Person person01 = new Person(PERSON01_NAME, 33);
		person01.setDogs(new ArrayList<Dog>());
		person01.getDogs().add(dog01);
		person01.getDogs().add(dog02);
		person01.getDogs().add(dog03);
		person01.setAddress(new Address("Street A", 30));
		dog01.setPerson(person01);
		dog02.setPerson(person01);
		dog03.setPerson(person01);

		Person person02 = new Person(PERSON02_NAME, 27);
		person02.setDogs(new ArrayList<Dog>());
		person02.getDogs().add(dog04);
		person02.getDogs().add(dog05);
		person02.getDogs().add(dog06);
		person02.setAddress(new Address("Street B", 60));
		dog04.setPerson(person02);
		dog05.setPerson(person02);
		dog06.setPerson(person02);

		Person person03 = new Person(PERSON03_NAME, 7);
		person03.setDogs(new ArrayList<Dog>());
		person03.getDogs().add(dog07);
		person03.getDogs().add(dog08);
		person03.getDogs().add(dog09);
		person03.setAddress(new Address("Street B", 90));
		dog07.setPerson(person03);
		dog08.setPerson(person03);
		dog09.setPerson(person03);

		Person person04 = new Person(PERSON04_NAME, 43);
		person04.setDogs(new ArrayList<Dog>());
		person04.getDogs().add(dog10);
		person04.getDogs().add(dog11);
		person04.getDogs().add(dog12);
		person04.setAddress(new Address("Street C", 120));
		dog10.setPerson(person04);
		dog11.setPerson(person04);
		dog12.setPerson(person04);

		Person person05 = new Person(PERSON05_NAME, 70);
		person05.setDogs(new ArrayList<Dog>());
		person05.getDogs().add(dog13);
		person05.getDogs().add(dog14);
		person05.getDogs().add(dog15);
		person05.getDogs().add(dog16);
		person05.setAddress(new Address("Street D", 150));
		dog13.setPerson(person05);
		dog14.setPerson(person05);
		dog15.setPerson(person05);
		dog16.setPerson(person05);

		Person person06 = new Person(PERSON06_NAME, 45);

		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(person01);
			em.persist(person02);
			em.persist(person03);
			em.persist(person04);
			em.persist(person05);
			em.persist(person06);

			em.persist(dog17);
			em.persist(dog18);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	private Date createNewDate(int day, int month, int year) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse("" + day + "/" + month + "/" + year);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
