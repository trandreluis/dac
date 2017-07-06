package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.entityGraph.CodeGeneratorEntityGraphDAO;

public class MainCodeGeneratorEntityGraph {

	public static void main(String[] args) {
		CodeGeneratorEntityGraphDAO dao = new CodeGeneratorEntityGraphDAO();
		try {
			dao.generateData();
		} finally {
			dao.close();
		}

	}

}
