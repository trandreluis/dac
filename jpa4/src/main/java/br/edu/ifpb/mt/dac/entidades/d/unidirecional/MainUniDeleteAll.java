package br.edu.ifpb.mt.dac.entidades.d.unidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.d.unidirecional.PostagemDAO;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		PostagemDAO dao = new PostagemDAO();
		try {
			List<Postagem> postagens = dao.getAll();
			for (Postagem postagem : postagens) {
				dao.delete(postagem);
			}
		} finally {
			dao.close();
		}
	}

}