package br.edu.ifpb.mt.daca;

import java.util.ArrayList;

import br.edu.ifpb.mt.daca.dao.bidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.bidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.bidirecional.CD;

public class MainBiArtistCdSave {

	public static void main(String[] args) throws DacaException {
		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDao = new CdDAO();
		try {
			// Criando o artista
			Artist artist = new Artist();

			artist.setFirstName("Primeiro nome");
			artist.setLastName("Último nome");
			artist.setAppearsOnCDs(new ArrayList<CD>());

			// Criando o cd
			CD cd = new CD();
			cd.setTitle("Título do CD");
			cd.setPrice(25.0f);
			cd.setDescription("Descrição do CD");
			cd.setCreatedByArtists(new ArrayList<Artist>());

			System.out.println(artist);
			System.out.println(cd);

			// Salvando o artista e o cd
			artistDao.save(artist);
			cdDao.save(cd);

			System.out.println(artist);
			System.out.println(cd);
			
			// Fazendo associação entre o artista e o CD
			artist.getAppearsOnCDs().add(cd);
			cd.getCreatedByArtists().add(artist);
			
			// Atualizando a associação
			artistDao.update(artist);
			System.out.println(artist);
			System.out.println(cd);
		} finally {
			artistDao.close();
		}
	}

}
