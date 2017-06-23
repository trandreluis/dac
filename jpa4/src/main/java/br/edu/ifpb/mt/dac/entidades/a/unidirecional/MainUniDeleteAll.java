package br.edu.ifpb.mt.dac.entidades.a.unidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.a.unidirecional.TuristaDAO;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		TuristaDAO dao = new TuristaDAO();
		try {
			List<Turista> turistas = dao.getAll();
			for (Turista turista : turistas) {
				dao.delete(turista);
			}
		} finally {
			dao.close();
		}
	}
	
}