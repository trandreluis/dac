package br.edu.ifpb.mt.daca;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifpb.mt.daca.dao.CdDAO;
import br.edu.ifpb.mt.daca.entities.CD;

public class MainCdSave {

	public static void main(String[] args) throws DacaException {
		CdDAO dao = new CdDAO();
		try {
			CD cd = new CD();
			
			cd.setTitle("Título do CD");
			cd.setDescription("Descrição do CD");
			cd.setPrice(35.0f);
			cd.setCover("cover".getBytes());
			
			Map<Integer, String> tracks = new HashMap<Integer, String>();
			tracks.put(1, "faixa 1");
			tracks.put(2, "faixa 2");
			tracks.put(3, "faixa 3");
			tracks.put(4, "faixa 4");
			tracks.put(5, "faixa 5");
			
			cd.setTracks(tracks);

			System.out.println(cd);

			dao.save(cd);

			System.out.println(cd);
		} finally {
			dao.close();
		}
	}

}
