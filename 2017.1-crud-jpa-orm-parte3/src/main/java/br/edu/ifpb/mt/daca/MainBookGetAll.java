package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.BookDAO;
import br.edu.ifpb.mt.daca.entities.Book;

public class MainBookGetAll {

	public static void main(String[] args) throws DacaException {

		BookDAO dao = new BookDAO();
		try {
			List<Book> books = dao.getAll();

			for (Book Book : books) {
				System.out.println(Book);
			}

		} finally {
			dao.close();
		}
	}

}
