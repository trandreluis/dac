package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.BookDAO;
import br.edu.ifpb.mt.daca.entities.Book;


public class MainBookUpdate {
	
	public static void main(String[] args) throws DacaException {
		BookDAO dao = new BookDAO();
		try {
			// Primeiro salvar
			Book book = new Book();
			
			book.setTitle("Introdução à plataforma Java EE 6 com Glassfish 3 " + 
					String.valueOf(System.currentTimeMillis()));
			book.setDescription("Java EE 6");
			book.setIsbn("9788539900961");
			book.setNbOfPage(537);
			book.setPrice(150f);
			book.setIllustrations(true);

			dao.save(book);

			System.out.println(book);
			
			// Depois atualizar
			book.setTitle("Outro t�tulo " + 
					String.valueOf(System.currentTimeMillis()));
			
			dao.update(book);
			
			System.out.println(book);
		} finally {
			dao.close();
		}
	}

}
