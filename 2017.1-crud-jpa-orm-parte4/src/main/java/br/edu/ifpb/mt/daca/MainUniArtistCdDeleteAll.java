package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.unidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.unidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.unidirecional.CD;

public class MainUniArtistCdDeleteAll {

	public static void main(String[] args) throws DacaException {
		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDAO = new CdDAO();
		try {
			List<Artist> artists = artistDao.getAll();
			for (Artist artist : artists) {
				artistDao.delete(artist);
			}
			List<CD> cds = cdDAO.getAll();
			for (CD cd : cds) {
				cdDAO.delete(cd);
			}
		} finally {
			artistDao.close();
		}
	}

}
