package br.edu.ifpb.mt.daca;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifpb.mt.daca.dao.CdDAO;
import br.edu.ifpb.mt.daca.entities.CD;


public class MainCdDelete {
	public static void main(String[] args) throws DacaException {
		CdDAO dao = new CdDAO();
		try {
			// Primeiro salvar
			CD cd = new CD();

			cd.setTitle("Título do CD");
			cd.setDescription("Descrição do CD");
			cd.setPrice(35.0f);
			cd.setCover("cover".getBytes());
			Map<Integer, String> tracks = new HashMap<Integer, String>();
			tracks.put(1, "faixa 1");
			tracks.put(2, "faixa 2");
			tracks.put(3, "faixa 3");
			cd.setTracks(tracks);
			
			dao.save(cd);

			System.out.println(dao.getAll().size());
			
			
			// Depois apagar
			
			dao.delete(cd);
			
			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}

}
