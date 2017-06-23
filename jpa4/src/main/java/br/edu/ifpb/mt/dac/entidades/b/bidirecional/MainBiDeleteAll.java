package br.edu.ifpb.mt.dac.entidades.b.bidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.b.bidirecional.DepartamentoDAO;

public class MainBiDeleteAll {

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