package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.BookDAO;
import br.edu.ifpb.mt.daca.entities.Book;


public class MainBookDeleteAll {

	public static void main(String[] args) throws DacaException {
		BookDAO dao = new BookDAO();
		try {
			List<Book> books = dao.getAll();
			for (Book book : books) {
				dao.delete(book);
			}
		} finally {
			dao.close();
		}
	}
	
}