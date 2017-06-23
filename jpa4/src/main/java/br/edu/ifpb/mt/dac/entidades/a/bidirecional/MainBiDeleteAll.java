package br.edu.ifpb.mt.dac.entidades.a.bidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.a.bidirecional.TuristaDAO;

public class MainBiDeleteAll {

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