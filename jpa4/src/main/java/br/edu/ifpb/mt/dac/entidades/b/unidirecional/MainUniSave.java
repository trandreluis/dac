package br.edu.ifpb.mt.dac.entidades.b.unidirecional;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.ifpb.mt.dac.dao.b.unidirecional.DepartamentoDAO;

public class MainUniSave {

	public static void main(String[] args) {
		DepartamentoDAO dao = new DepartamentoDAO();
		try {
			Departamento departamento = new Departamento();

			departamento.setNome("Tecnologia da Informação");
			departamento.setSigla("TI");

			Empregado empregado = new Empregado();
			
			empregado.setNome("Lucio");
			empregado.setSobreNome("Santos");
			empregado.setSalario(new BigDecimal(1000000));
			empregado.setDataNascimento(new Date());
			
			departamento.setGerente(empregado);
			
			System.out.println(departamento);
			System.out.println(empregado);

			dao.save(departamento);

			System.out.println(departamento);
			System.out.println(empregado);
			
		} finally {
			dao.close();
		}
	}
	
}