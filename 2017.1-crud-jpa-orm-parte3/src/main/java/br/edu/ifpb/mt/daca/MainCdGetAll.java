package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.CdDAO;
import br.edu.ifpb.mt.daca.entities.CD;

public class MainCdGetAll {

	public static void main(String[] args) throws DacaException {

		CdDAO dao = new CdDAO();
		try {
			List<CD> cds = dao.getAll();

			for (CD cd : cds) {
				System.out.println(cd);
			}

		} finally {
			dao.close();
		}
	}

}
