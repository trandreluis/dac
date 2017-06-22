package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.bidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.bidirecional.CD;

public class MainBiArtistCdGetAll {

	public static void main(String[] args) throws DacaException {

		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDAO = new CdDAO();

		try {
			List<Artist> artists = artistDao.getAll();
			System.out.println("Artists:");
			for (Artist artist : artists) {
				System.out.println(artist.getFirstName());
			}

			List<CD> cds = cdDAO.getAll();
			System.out.println("CDs:");
			for (CD cd : cds) {
				System.out.println(cd.getTitle());
			}
		} finally {
			artistDao.close();
		}
	}

}
