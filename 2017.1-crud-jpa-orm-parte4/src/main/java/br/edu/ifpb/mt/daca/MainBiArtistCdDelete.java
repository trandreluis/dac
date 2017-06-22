package br.edu.ifpb.mt.daca;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.mt.daca.dao.bidirecional.ArtistDAO;
import br.edu.ifpb.mt.daca.dao.bidirecional.CdDAO;
import br.edu.ifpb.mt.daca.entities.bidirecional.Artist;
import br.edu.ifpb.mt.daca.entities.bidirecional.CD;

public class MainBiArtistCdDelete {

	public static void main(String[] args) throws DacaException {
		ArtistDAO artistDao = new ArtistDAO();
		CdDAO cdDAO = new CdDAO();
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
			cdDAO.save(cd);

			System.out.println(artist);
			System.out.println(cd);
			
			// Fazendo associação entre o artista e o CD
			artist.getAppearsOnCDs().add(cd);
			cd.getCreatedByArtists().add(artist);
			
			// Atualizando a associação
			artist = artistDao.update(artist);
			System.out.println(artist);
			System.out.println(cd);
			
			// Depois apagar. A associação não é desfeita automaticamente caso 
			// a coleção esteja carregada em memória.
			// Antes de remover a entidade é preciso desfazer a associação.
			artist.getAppearsOnCDs().remove(cd);
			cd.getCreatedByArtists().remove(artist);
			artist = artistDao.update(artist);
			
			cdDAO.delete(cd);
			artistDao.delete(artist);
			
		} finally {
			artistDao.close();
		}
	}

}
