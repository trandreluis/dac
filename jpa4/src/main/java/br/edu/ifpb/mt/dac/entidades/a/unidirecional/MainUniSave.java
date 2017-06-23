package br.edu.ifpb.mt.dac.entidades.a.unidirecional;

import java.util.Date;

import br.edu.ifpb.mt.dac.dao.a.unidirecional.TuristaDAO;

public class MainUniSave {

	public static void main(String[] args) {
		TuristaDAO dao = new TuristaDAO();
		try {
			Turista turista = new Turista();

			turista.setNome("Andre");
			turista.setSobrenome("Luis");
			turista.setDataNascimento(new Date());
			
			Passaporte passaporte = new Passaporte();
			passaporte.setNumero(1111);
			passaporte.setEndereco("Sertania");
			passaporte.setEstado("Pernambuco");
			passaporte.setPais("Brasil");
			
			turista.setPassaporte(passaporte);
			
			System.out.println(turista);
			System.out.println(passaporte);

			dao.save(turista);

			System.out.println(turista);
			System.out.println(passaporte);
			
		} finally {
			dao.close();
		}
	}
	
}