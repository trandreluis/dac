package br.edu.ifpb.mt.daca;

import java.util.ArrayList;

import br.edu.ifpb.mt.daca.dao.unidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.unidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.unidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.unidirecional.CD;

public class MainUniArtistCdSave {

	public static void main(String[] args) throws DacaException {
		ArtistDAO daoArtist = new ArtistDAO();
		CdDAO cdDAO = new CdDAO();
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

			System.out.println(artist);
			System.out.println(cd);

			// Salvando o artista e o cd
			daoArtist.save(artist);
			cdDAO.save(cd);

			System.out.println(artist);
			System.out.println(cd);
			
			// Fazendo associação entre o artista e o CD
			artist.getAppearsOnCDs().add(cd);
			
			// Atualizando a associação
			daoArtist.update(artist);
			System.out.println(artist);
			System.out.println(cd);
		} finally {
			daoArtist.close();
		}
	}

}
