package br.edu.ifpb.mt.dac.entidades.c.bidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.c.bidirecional.CelularDAO;

public class MainBiDeleteAll {

	public static void main(String[] args) {
		CelularDAO dao = new CelularDAO();
		try {
			List<Celular> celulares = dao.getAll();
			for (Celular celular : celulares) {
				dao.delete(celular);
			}
		} finally {
			dao.close();
		}
	}
	
}