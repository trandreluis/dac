package br.edu.ifpb.mt.dac.entidades.b.bidirecional;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.ifpb.mt.dac.dao.b.bidirecional.DepartamentoDAO;

public class MainBiSave {

	public static void main(String[] args) {
		DepartamentoDAO dao = new DepartamentoDAO();
		try {
			Departamento departamento = new Departamento();

			departamento.setNome("Tecnologia da Informação");
			departamento.setSigla("TI");

			Empregado empregado = new Empregado();
			
			empregado.setNome("Luciano");
			empregado.setSobreNome("Santos");
			empregado.setSalario(new BigDecimal(1000000));
			empregado.setDataNascimento(new Date());
			
			departamento.setGerente(empregado);
			empregado.setGerencia(departamento);
			
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