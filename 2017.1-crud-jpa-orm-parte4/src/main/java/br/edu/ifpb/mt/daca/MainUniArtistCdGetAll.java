package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.unidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.unidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.unidirecional.CD;

public class MainUniArtistCdGetAll {

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
