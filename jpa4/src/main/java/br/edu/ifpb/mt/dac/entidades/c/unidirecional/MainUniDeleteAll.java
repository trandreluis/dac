package br.edu.ifpb.mt.dac.entidades.c.unidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.c.unidirecional.CelularDAO;


public class MainUniDeleteAll {

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