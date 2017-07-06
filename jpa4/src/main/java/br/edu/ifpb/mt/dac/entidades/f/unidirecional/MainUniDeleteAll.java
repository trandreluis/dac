package br.edu.ifpb.mt.dac.entidades.f.unidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.f.unidirecional.ColaboradorDAO;
import br.edu.ifpb.mt.dac.dao.f.unidirecional.ProjetoDAO;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		ProjetoDAO daoProjeto = new ProjetoDAO();
		ColaboradorDAO daoColcaborador = new ColaboradorDAO();
		try {
			List<Projeto> projetos = daoProjeto.getAll();
			for (Projeto projeto : projetos) {
				daoProjeto.delete(projeto);
			}
			List<Colaborador> colaboradores = daoColcaborador.getAll();
			for (Colaborador cd : colaboradores) {
				daoColcaborador.delete(cd);
			}
		} finally {
			daoProjeto.close();
		}
	}

}