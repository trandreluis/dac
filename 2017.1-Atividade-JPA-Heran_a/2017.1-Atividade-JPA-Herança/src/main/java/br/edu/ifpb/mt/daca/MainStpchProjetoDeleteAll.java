package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.singleTablePerClassHierarchy.ProjetoDAO;
import br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy.Projeto;

public class MainStpchProjetoDeleteAll {

	public static void main(String[] args) {
		ProjetoDAO dao = new ProjetoDAO();
		try {
			List<Projeto> projetos = dao.getAll();
			for (Projeto proj : projetos) {
				dao.delete(proj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			dao.close();
		}
	}

}