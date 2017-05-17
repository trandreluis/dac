package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.UserDAO;
import br.edu.ifpb.mt.daca.entities.User;

public class MainDeleteAll {

	public static void main(String[] args) throws DacaException {
		UserDAO dao = new UserDAO();
		try {
			List<User> usuarios = dao.getAll();
			for (User usuario : usuarios) {
				dao.delete(usuario);
			}
		} finally {
			dao.close();
		}
	}

}
