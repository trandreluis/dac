package br.edu.ifpb.mt.dac.entidades.d.unidirecional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "COMENTARIO_UNI")
@Table(name = "TB_COMENTARIO")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CONTEUDO")
	private String conteudo;
	
	@Column(name = "LIKES")
	private Integer likes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public String toString() {
		return "ID: "+this.id+" | CONTEUDO: "+this.conteudo+" | QTD. LIKES: "+this.likes;
	}
	
}