package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.tablePerConcreteClass.EmpregadoDAO;
import br.edu.ifpb.mt.daca.entities.tablePerConcreteClass.Empregado;

public class MainTpccEmpregadoDeleteAll {

	public static void main(String[] args) {
		EmpregadoDAO dao = new EmpregadoDAO();
		try {
			List<Empregado> empregados = dao.getAll();
			for (Empregado emp : empregados) {
				dao.delete(emp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			dao.close();
		}
	}

}
