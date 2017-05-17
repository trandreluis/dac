package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

public class MainGetAll {

	public static void main(String[] args) throws DacaException {

		UserDAO dao = new UserDAO();
		try {
			List<User> usuarios = dao.getAll();

			for (User user : usuarios) {
				System.out.println(user);
			}

		} finally {
			dao.close();
		}
	}

}
