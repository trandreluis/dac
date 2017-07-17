package br.edu.ifpb.mt.daca;

import br.edu.ifpb.mt.daca.dao.joinedSubclass.VeiculoDAO;
import br.edu.ifpb.mt.daca.entities.joinedSubclass.Bicicleta;
import br.edu.ifpb.mt.daca.entities.joinedSubclass.Carro;

public class MainJsVeiculoSave {

	public static void main(String[] args) {
		VeiculoDAO dao = new VeiculoDAO();
		try {

			Carro carro = new Carro();

			carro.setNome("Civic");
			carro.setConstrutora("Honda");
			carro.setNumDePortas(4);
			carro.setNumMaxPassageiros(5);

			System.out.println(carro);

			// Salvando carro
			dao.save(carro);

			Bicicleta bicicleta = new Bicicleta();

			bicicleta.setNome("CL-12");
			bicicleta.setConstrutora("Caloi");
			bicicleta.setAlturaDoSelimCm(120);
			bicicleta.setNumMaxPassageiros(1);

			System.out.println(bicicleta);

			// Salvando bicicleta
			dao.save(bicicleta);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}

}