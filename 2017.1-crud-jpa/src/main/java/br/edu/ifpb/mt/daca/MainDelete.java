package br.edu.ifpb.mt.daca;

import java.util.Date;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

public class MainDelete {

	public static void main(String[] args) throws DacaException {
		UserDAO dao = new UserDAO();
		try {
			// Primeiro salvar
			User user = new User();

			user.setBirthday(new Date());
			user.setEmail("email@gmail.com");
			user.setFirstName("Sicrano");
			user.setLastName("Silva");

			dao.save(user);

			System.out.println(dao.getAll().size());

			// Depois apagar

			dao.delete(user);

			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}

}
