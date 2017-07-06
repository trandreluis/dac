package br.edu.ifpb.mt.dac.entidades.g.bidirecional;

import java.util.List;

import br.edu.ifpb.mt.dac.dao.g.bidirecional.AutorDAO;
import br.edu.ifpb.mt.dac.dao.g.bidirecional.LivroDAO;

public class MainUniDeleteAll {

	public static void main(String[] args) {
		LivroDAO daoLivro = new LivroDAO();
		AutorDAO daoAutor = new AutorDAO();
		try {
			List<Livro> livros = daoLivro.getAll();
			for (Livro livro : livros) {
				daoLivro.delete(livro);
			}
			List<Autor> autores = daoAutor.getAll();
			for (Autor autor : autores) {
				daoAutor.delete(autor);
			}
		} finally {
			daoLivro.close();
		}
	}

}