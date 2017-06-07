package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.NewsEIDAO;
import br.edu.ifpb.mt.daca.entities.embeddedid.NewsEI;


public class MainNewsEIGetAll {

	public static void main(String[] args) throws DacaException {

		NewsEIDAO dao = new NewsEIDAO();
		try {
			List<NewsEI> newsEIs = dao.getAll();

			for (NewsEI newsEI : newsEIs) {
				System.out.println(newsEI);
			}

		} finally {
			dao.close();
		}
	}

}
