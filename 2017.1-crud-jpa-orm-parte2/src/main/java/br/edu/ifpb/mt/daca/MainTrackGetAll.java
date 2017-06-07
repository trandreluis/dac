package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.TrackDAO;
import br.edu.ifpb.mt.daca.entities.Track;

public class MainTrackGetAll {

	public static void main(String[] args) throws DacaException {

		TrackDAO dao = new TrackDAO();
		try {
			List<Track> tracks = dao.getAll();

			for (Track track : tracks) {
				System.out.println(track);
			}

		} finally {
			dao.close();
		}
	}

}
