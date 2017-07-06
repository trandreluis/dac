package br.edu.ifpb.mt.dac.entidades.e;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.e.FuncionarioDAO;;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		FuncionarioDAO dao = new FuncionarioDAO();
		try {
			List<Funcionario> funcionarios = dao.getAll();
			for (Funcionario funcionario : funcionarios) {
				dao.delete(funcionario);
			}
		} finally {
			dao.close();
		}
	}

}