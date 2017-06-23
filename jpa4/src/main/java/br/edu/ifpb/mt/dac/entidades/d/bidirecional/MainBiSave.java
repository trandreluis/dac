package br.edu.ifpb.mt.dac.entidades.d.bidirecional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.mt.dac.dao.d.bidirecional.PostagemDAO;

public class MainBiSave {

	public static void main(String[] args) {
		PostagemDAO dao = new PostagemDAO();
		try {
			Postagem postagem = new Postagem();
			postagem.setTitulo("Java 9 - O que muda?");
			postagem.setDataPublicacao(new Date());

			Comentario comentario = new Comentario();
			comentario.setConteudo("Já tem data oficial de lançamento?");
			comentario.setLikes(100);

			Comentario comentario2 = new Comentario();
			comentario2.setConteudo("E o novo Java EE?");
			comentario2.setLikes(57);

			Comentario comentario3 = new Comentario();
			comentario3.setConteudo("O que vem de novo na programação funcional?");
			comentario3.setLikes(57);

			comentario.setPostagem(postagem);
			comentario2.setPostagem(postagem);
			comentario3.setPostagem(postagem);
			
			
			List<Comentario> comentarios = Arrays.asList(comentario, comentario2, comentario3);

			postagem.setComentarios(comentarios);

			System.out.println(postagem);
			System.out.println(comentarios);

			dao.save(postagem);

			System.out.println(postagem);
			System.out.println(comentarios);

		} finally {
			dao.close();
		}
	}

}