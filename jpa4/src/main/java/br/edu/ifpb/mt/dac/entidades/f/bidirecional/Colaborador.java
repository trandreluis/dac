package br.edu.ifpb.mt.dac.entidades.f.bidirecional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "COLABORADOR_BI")
@Table(name = "TB_COLABORADOR")
public class Colaborador {

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

	@Column(name = "SALARIO")
	private BigDecimal salario;

	@ManyToMany(mappedBy = "integrantes")
	private List<Projeto> projetos;

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

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public String toString() {
		return "ID: " + this.id + " | NOME: " + this.nome + " | SOBRENOME: " + this.sobreNome
				+ " | DATA DE NASCIMENTO: " + this.dataNascimento + " | SALARIO: " + this.salario + " | QTD. PROJETOS: "
				+ this.projetos.size();
	}

}