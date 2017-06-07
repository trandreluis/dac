package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.NewsEIDAO;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsEI;

public class MainNewsEIDeleteAll {

	public static void main(String[] args) throws DacaException {
		NewsEIDAO dao = new NewsEIDAO();
		try {
			List<NewsEI> newsEIs = dao.getAll();
			for (NewsEI newsEI : newsEIs) {
				dao.delete(newsEI);
			}
		} finally {
			dao.close();
		}
	}
	
}
