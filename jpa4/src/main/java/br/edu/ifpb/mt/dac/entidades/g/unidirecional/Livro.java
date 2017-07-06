package br.edu.ifpb.mt.dac.entidades.g.unidirecional;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "LIVRO_UNI")
@Table(name = "TB_LIVRO")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "EDICAO")
	private Integer edicao;

	@Column(name = "ANO")
	private Integer ano;

	@ManyToMany
	@JoinTable(name = "TB_LIVRO_AUTOR", joinColumns = @JoinColumn(name = "LIVRO_FK"), inverseJoinColumns = @JoinColumn(name = "AUTOR_FK"))
	private List<Autor> autores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String toString() {
		return "ID: " + this.id + " | NOME: " + this.nome + " | EDICAO: " + this.edicao + " | ANO: " + this.ano
				+ " | QTD. AUTORES: " + this.autores.size();
	}

}