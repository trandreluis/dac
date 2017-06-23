package br.edu.ifpb.mt.dac.entidades.b.unidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.b.unidirecional.DepartamentoDAO;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		DepartamentoDAO dao = new DepartamentoDAO();
		try {
			List<Departamento> departamentos = dao.getAll();
			for (Departamento departamento : departamentos) {
				dao.delete(departamento);
			}
		} finally {
			dao.close();
		}
	}
	
}