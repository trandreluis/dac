package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.NewsEIDAO;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsEI;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsIdEI;


public class MainNewsEISave {

	public static void main(String[] args) throws DacaException {
		NewsEIDAO dao = new NewsEIDAO();
		try {
			NewsEI newsEI = new NewsEI();
			
			NewsIdEI id = new NewsIdEI();
			id.setLanguage("pt_BR");
			id.setTitle("title " + System.currentTimeMillis());
			newsEI.setId(id);
			newsEI.setContent("content");
			
			System.out.println(newsEI);
			
			dao.save(newsEI);

			System.out.println(newsEI);
		} finally {
			dao.close();
		}
	}

}
