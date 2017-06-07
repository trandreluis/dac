package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.NewsICDAO;
import br.edu.ifpb.mt.daca.entities.idclass.NewsIC;
import br.edu.ifpb.mt.daca.entities.idclass.NewsIdIC;


public class MainNewsICSave {

	public static void main(String[] args) throws DacaException {
		NewsICDAO dao = new NewsICDAO();
		try {
			NewsIC newsIC = new NewsIC();
			
			NewsIdIC id = new NewsIdIC();
			id.setLanguage("pt_BR");
			id.setTitle("title " + System.currentTimeMillis());
			newsIC.setLanguage(id.getLanguage());
			newsIC.setTitle(id.getTitle());
			newsIC.setContent("content");
			
			System.out.println(newsIC);
			
			dao.save(newsIC);

			System.out.println(newsIC);
		} finally {
			dao.close();
		}
	}

}
