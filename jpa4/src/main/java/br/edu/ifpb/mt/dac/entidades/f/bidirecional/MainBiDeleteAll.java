package br.edu.ifpb.mt.dac.entidades.f.bidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.f.bidirecional.ColaboradorDAO;
import br.edu.ifpb.mt.dac.dao.f.bidirecional.ProjetoDAO;

public class MainBiDeleteAll {

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