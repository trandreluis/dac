package br.edu.ifpb.mt.dac.entidades.b.bidirecional;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "EMPREGADO_BI")
@Table(name = "TB_EMPREGADO")
public class Empregado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "gerente")
	private Departamento gerencia;

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

	public Departamento getGerencia() {
		return gerencia;
	}

	public void setGerencia(Departamento gerencia) {
		this.gerencia = gerencia;
	}

	public String toString() {
		return "ID: " + this.id + " | NOME: " + this.nome + " | SOBRENOME: " + this.sobreNome
				+ " | DATA DE NASCIMENTO: " + this.dataNascimento + " | SALARIO: " + this.salario + " | DEPARTAMENTO: "
				+ this.gerencia.getNome();
	}

}