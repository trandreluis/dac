package br.edu.ifpb.mt.dac.entidades.g.bidirecional;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "AUTOR_BI")
@Table(name = "TB_AUTOR")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "SOBRENOME")
	private String sobreNome;

	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	
	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros;

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

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public String toString() {
		return "ID: " + this.id + " | NOME: " + this.nome + " | SOBRENOME: " + this.sobreNome
				+ " | DATA DE NASCIMENTO: " + this.dataNascimento + " | QTD. LIVROS: "+this.livros.size();
	}

}