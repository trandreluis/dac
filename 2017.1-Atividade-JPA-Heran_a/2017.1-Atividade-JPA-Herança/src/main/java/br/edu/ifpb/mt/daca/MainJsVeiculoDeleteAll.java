package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.joinedSubclass.VeiculoDAO;
import br.edu.ifpb.mt.daca.entities.joinedSubclass.Veiculo;

public class MainJsVeiculoDeleteAll {

	public static void main(String[] args) {
		VeiculoDAO dao = new VeiculoDAO();
		try {
			List<Veiculo> veiculos = dao.getAll();
			for (Veiculo veiculo : veiculos) {
				dao.delete(veiculo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			dao.close();
		}
	}

}