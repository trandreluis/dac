package br.edu.ifpb.mt.dac.entidades.g.bidirecional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.dao.g.bidirecional.AutorDAO;
import br.edu.ifpb.mt.dac.dao.g.bidirecional.LivroDAO;

public class MainUniSave {

	public static void main(String[] args) {
		LivroDAO daoLivro = new LivroDAO();
		AutorDAO daoAutor = new AutorDAO();
		try {
			Livro livro = new Livro();
			livro.setAno(1996);
			livro.setEdicao(3);
			livro.setNome("Estrutura de Dados e Algoritmos");
			livro.setAutores(new ArrayList<Autor>());

			Autor autor = new Autor();
			autor.setNome("Gang");
			autor.setSobreNome("of Four");
			autor.setDataNascimento(new Date());
			autor.setLivros(new ArrayList<Livro>());

			List<Autor> autores = Arrays.asList(autor);
			List<Livro> livros = Arrays.asList(livro);

			System.out.println(livro);
			System.out.println(autor);

			daoLivro.save(livro);
			daoAutor.save(autor);

			System.out.println(livro);
			System.out.println(autor);

			livro.setAutores(autores);
			autor.setLivros(livros);

			daoLivro.update(livro);

			System.out.println(livro);
			System.out.println(autor);
		} finally {
			daoLivro.close();
			daoAutor.close();
		}
	}

}