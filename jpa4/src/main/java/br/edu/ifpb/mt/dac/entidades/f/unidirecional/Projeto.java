package br.edu.ifpb.mt.dac.entidades.f.unidirecional;

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

@Entity(name = "PROJETO_UNI")
@Table(name = "TB_PROJETO")
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DURACAO")
	private Integer duracao;

	@ManyToMany
	@JoinTable(name = "TB_PROJETO_COLABORADOR", joinColumns = @JoinColumn(name = "PROJETO_FK"), inverseJoinColumns = @JoinColumn(name = "COLABORADOR_FK"))
	private List<Colaborador> integrantes;

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

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public List<Colaborador> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Colaborador> integrantes) {
		this.integrantes = integrantes;
	}

	public String toString() {
		return "ID: " + this.id + " | NOME: " + this.nome + " | DURACAO: " + this.duracao + " | QTD. INTEGRANTES: "
				+ this.integrantes.size();
	}

}