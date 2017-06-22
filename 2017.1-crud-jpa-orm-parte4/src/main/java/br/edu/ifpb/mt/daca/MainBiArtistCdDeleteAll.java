package br.edu.ifpb.mt.daca;

import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.bidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.bidirecional.CD;

public class MainBiArtistCdDeleteAll {

	public static void main(String[] args) throws DacaException {
		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDao = new CdDAO();
		try {
			List<Artist> artists = artistDao.getAll();
			for (Artist artist : artists) {
				artistDao.delete(artist);
			}
			
			List<CD> cds = cdDao.getAll();
			for (CD cd : cds) {
				cdDao.delete(cd);
			}
		} finally {
			artistDao.close();
		}
	}

}
