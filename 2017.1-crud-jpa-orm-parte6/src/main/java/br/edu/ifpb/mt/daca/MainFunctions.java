package br.edu.ifpb.mt.daca;

import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON03_NAME;
import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON04_NAME;
import static br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO.PERSON05_NAME;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.DogDAO;
import br.edu.ifpb.mt.daca.dao.PersonDAO;

public class MainFunctions {

	public static void main(String[] args) throws DacaException {
		// AVG - Does a number average
		// COUNT - Counts the records amount found by the query
		// MAX - Gets the higher value of a column
		// MIN - Gets the lower value of a column
		// TRIM - Removes the white space at the begging/end of the text
		// SUM - Sums all the values of a column
		// UPPER - Modifies all the column text to UPPER CASE
		// LOWER - Modifies all the column text to lower case
		// MOD - Returns the modulus of a column
		// LENGTH - Returns the size of a String
		// SQRT - Returns the square root of a number

		PersonDAO personDAO = new PersonDAO();
		DogDAO dogDAO = new DogDAO();

		try {
			System.out.println("### getPersonsAgeAverage:");
			Double average = personDAO.getPersonsAgeAverage();
			System.out.println(average);

			System.out.println("### getPersonsWithDogsWeightHigherThan:");
			List<Object[]> personsFilteredByDogsWeight = personDAO.getPersonsWithDogsWeightHigherThan(4d);

			for (Object[] objects : personsFilteredByDogsWeight) {
				String name = (String) objects[0];
				Long count = (Long) objects[1];
				System.out.println("The person : " + name + " has " + count + " dogs with the weight > 4");
			}

			System.out.println("### getDogMinAndMaxWeight:");
			List<Object[]> dogsMinAndMaxWeightList = dogDAO.getDogMinAndMaxWeight();
			Object[] dogMinAndMaxWeightResult = dogsMinAndMaxWeightList.get(0);
			System.out.println("Min: " + dogMinAndMaxWeightResult[0] + " Max: " + dogMinAndMaxWeightResult[1]);

			System.out.println("### getTheSumOfAllAges:");
			Long sumOfAllAges = personDAO.getTheSumOfAllAges();
			System.out.println("All summed ages are: " + sumOfAllAges);

			System.out.println("### getLoweredCaseNameFromUpperCase:");
			String loweredCaseName = personDAO.getLoweredCaseNameFromUpperCase(PERSON03_NAME);
			System.out.println(loweredCaseName);

			System.out.println("### getPersonAgeMode:");
			Integer personAgeMod = personDAO.getPersonAgeMod(PERSON05_NAME, 6);
			System.out.println("Person modulus age: " + personAgeMod);

			System.out.println("### getPersonAgeSqrtUsingTrim:");
			Double personAgeSqrt = personDAO.getPersonAgeSqrtUsingTrim(" " + PERSON04_NAME + " ");
			System.out.println("Person sqrt age: " + personAgeSqrt);

			System.out.println("### getPersonByHavingDogAmountHigherThan:");
			List<Object[]> personsByDogsAmount = personDAO.getPersonByHavingDogAmountHigherThan(3);

			for (Object[] objects : personsByDogsAmount) {
				String name = (String) objects[0];
				Long count = (Long) objects[1];
				System.out.println(name + " has " + count + " dogs");
			}

		} finally {
			personDAO.close();
			dogDAO.close();
		}

	}

}
