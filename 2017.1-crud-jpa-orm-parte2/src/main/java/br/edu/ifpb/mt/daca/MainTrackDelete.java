package br.edu.ifpb.mt.daca;

import java.math.BigDecimal;

import br.edu.ifpb.mt.daca.dao.TrackDAO;
import br.edu.ifpb.mt.daca.entities.Track;

public class MainTrackDelete {

	public static void main(String[] args) throws DacaException {
		TrackDAO dao = new TrackDAO();
		try {
			// Primeiro salvar
			Track track = new Track();

			track.setTitle("title");
			track.setDescription("description");
			track.setDuration(new BigDecimal(100));
			track.setWav("<bytes da música seriam colocados aqui>".getBytes());

			dao.save(track);

			System.out.println(dao.getAll().size());

			// Depois apagar

			dao.delete(track);

			System.out.println(dao.getAll().size());
		} finally {
			dao.close();
		}
	}

}
