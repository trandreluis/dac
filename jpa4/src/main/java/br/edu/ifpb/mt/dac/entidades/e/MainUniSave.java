package br.edu.ifpb.mt.dac.entidades.e;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.dao.e.FuncionarioDAO;

public class MainUniSave {

	public static void main(String[] args) {
		FuncionarioDAO dao = new FuncionarioDAO();
		try {
			Funcionario gerente = new Funcionario();
			gerente.setNome("Fulano");
			gerente.setSobreNome("Gerente da Silva");
			gerente.setDataNascimento(new Date());
			gerente.setSalario(new BigDecimal(10000));

			Funcionario subordinado = new Funcionario();
			subordinado.setNome("Fulano");
			subordinado.setSobreNome("Subordinado da Silva");
			subordinado.setDataNascimento(new Date());
			subordinado.setSalario(new BigDecimal(100));

			Funcionario subordinado2 = new Funcionario();
			subordinado2.setNome("Fulano");
			subordinado2.setSobreNome("Subordinado da Silva");
			subordinado2.setDataNascimento(new Date());
			subordinado2.setSalario(new BigDecimal(100));

			Funcionario subordinado3 = new Funcionario();
			subordinado3.setNome("Fulano");
			subordinado3.setSobreNome("Subordinado da Silva");
			subordinado3.setDataNascimento(new Date());
			subordinado3.setSalario(new BigDecimal(100));

			List<Funcionario> funcionarios = Arrays.asList(subordinado, subordinado2, subordinado3);

			gerente.setGerencia(funcionarios);

			System.out.println(gerente);
			System.out.println(funcionarios);

			dao.save(gerente);

			System.out.println(gerente);
			System.out.println(funcionarios);

		} finally {
			dao.close();
		}
	}

}