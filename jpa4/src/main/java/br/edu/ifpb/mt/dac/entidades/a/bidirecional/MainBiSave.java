package br.edu.ifpb.mt.dac.entidades.a.bidirecional;

import java.util.Date;

import br.edu.ifpb.mt.dac.dao.a.bidirecional.TuristaDAO;

public class MainBiSave {

	public static void main(String[] args) {
		
		TuristaDAO dao = new TuristaDAO();
		
		try {
		
			Turista turista = new Turista();

			turista.setNome("Maria");
			turista.setSobrenome("Lopes");
			turista.setDataNascimento(new Date());
			
			Passaporte passaporte = new Passaporte();
			passaporte.setNumero(1111);
			passaporte.setEndereco("Monteiro");
			passaporte.setEstado("Paraiba");
			passaporte.setPais("Brasil");
			
			turista.setPassaporte(passaporte);
			passaporte.setTurista(turista);
			
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