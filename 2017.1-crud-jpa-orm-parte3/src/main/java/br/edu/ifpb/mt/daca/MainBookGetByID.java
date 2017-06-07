package br.edu.ifpb.mt.daca;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.mt.daca.dao.BookDAO;
import br.edu.ifpb.mt.daca.entities.Book;


public class MainBookGetByID {
	public static void main(String[] args) throws DacaException {
		BookDAO dao = new BookDAO();
		try {
			// Primeiro salvar
			Book book = new Book();

			book.setTitle("Introdução à plataforma Java EE 6 com Glassfish 3");
			book.setDescription("Java EE 6");
			book.setIsbn("9788539900961");
			book.setNbOfPage(537);
			book.setPrice(150f);
			book.setIllustrations(true);
			List<String> tags = new ArrayList<String>();
			tags.add("tag1");
			tags.add("tag2");
			book.setTags(tags);

			dao.save(book);

			// Depois recuperar pelo identificador
			
			Book resultado = dao.getByID(book.getId());
			
			System.out.println(book.equals(resultado));
		} finally {
			dao.close();
		}
	}

}
