package br.edu.ifpb.mt.daca;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.bidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.bidirecional.CD;

public class MainBiArtistCdUpdate {

	public static void main(String[] args) throws DacaException {
		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDao = new CdDAO();
		try {
			// Criando o artista
			Artist artist = new Artist();

			artist.setFirstName("Primeiro nome");
			artist.setLastName("Último nome");
			List<CD> appearsOnCDs = new ArrayList<CD>();
			artist.setAppearsOnCDs(appearsOnCDs);

			// Criando o cd
			CD cd = new CD();
			cd.setTitle("Título do CD");
			cd.setPrice(25.0f);
			cd.setDescription("Descrição do CD");
			List<Artist> createdByArtists = new ArrayList<Artist>();
			cd.setCreatedByArtists(createdByArtists);

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

			// Depois atualizar
			artist.setFirstName("Primeiro nome modificado");
			cd.setDescription("Outra descrição do CD");

			artistDao.update(artist);
			cdDao.update(cd);

			System.out.println(artist);
			System.out.println(cd);
		} finally {
			artistDao.close();
		}
	}

}
