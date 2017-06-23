package br.edu.ifpb.mt.dac.entidades.c.bidirecional;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "CHAMADA_BI")
@Table(name = "TB_CHAMADA")
public class Chamada {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DURACAO")
	private Integer duracao;

	@Column(name = "HORA_INICIO")
	private Date horaInicio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CELULAR_FK")
	private Celular celular;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Celular getCelular() {
		return celular;
	}

	public void setCelular(Celular celular) {
		this.celular = celular;
	}

	public String toString() {
		return "ID: " + this.id + " | DURACAO: " + this.duracao + " | HORA DE INICIO: " + this.horaInicio;
	}

}