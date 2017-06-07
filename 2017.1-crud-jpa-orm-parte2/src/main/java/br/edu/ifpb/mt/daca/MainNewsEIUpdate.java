package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.NewsEIDAO;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsEI;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsIdEI;



public class MainNewsEIUpdate {
	
	public static void main(String[] args) throws DacaException {
		NewsEIDAO dao = new NewsEIDAO();
		try {
			// Primeiro salvar
			NewsEI newsEI = new NewsEI();
			
			NewsIdEI id = new NewsIdEI();
			id.setLanguage("pt_BR");
			id.setTitle("title " + System.currentTimeMillis());
			newsEI.setId(id);
			newsEI.setContent("content");

			dao.save(newsEI);

			System.out.println(newsEI);
			
			// Depois atualizar
			newsEI.setContent("outro conteúdo");
			
			dao.update(newsEI);
			
			System.out.println(newsEI);
		} finally {
			dao.close();
		}
	}

}
