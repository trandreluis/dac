package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.CodeGeneratorDAO;

public class MainCodeGenerator {

	public static void main(String[] args) {
		CodeGeneratorDAO dao = new CodeGeneratorDAO();
		try {
			dao.generateData();
		} finally {
			dao.close();
		}

	}

}
