package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.NewsICDAO;
import br.edu.ifpb.mt.daca.entities.idclass.NewsIC;


public class MainNewsICGetAll {

	public static void main(String[] args) throws DacaException {

		NewsICDAO dao = new NewsICDAO();
		try {
			List<NewsIC> newsICs = dao.getAll();

			for (NewsIC newsIC : newsICs) {
				System.out.println(newsIC);
			}

		} finally {
			dao.close();
		}
	}

}
