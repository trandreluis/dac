package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.tablePerConcreteClass.EmpregadoDAO;
import br.edu.ifpb.mt.daca.entities.tablePerConcreteClass.EmpTempoIntegral;
import br.edu.ifpb.mt.daca.entities.tablePerConcreteClass.EmpTempoParcial;

public class MainTpccEmpregadoSave {

	public static void main(String[] args) {
		EmpregadoDAO dao = new EmpregadoDAO();
		try {
			EmpTempoIntegral empregadoIntegral = new EmpTempoIntegral();

			empregadoIntegral.setNome("Luis");
			empregadoIntegral.setSalario(1000.0);

			System.out.println(empregadoIntegral);

			// Salvando emprepago integral
			dao.save(empregadoIntegral);

			EmpTempoParcial empregadoParcial = new EmpTempoParcial();

			empregadoParcial.setNome("Carlos");
			empregadoParcial.setValorHora(38.0);

			System.out.println(empregadoParcial);

			// Salvando emprepago parcial
			dao.save(empregadoParcial);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}

}
