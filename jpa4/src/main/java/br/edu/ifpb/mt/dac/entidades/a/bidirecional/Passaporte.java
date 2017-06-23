package br.edu.ifpb.mt.dac.entidades.a.bidirecional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "PASSAPORTE_BI")
@Table(name = "TB_PASSAPORTE")
public class Passaporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NUMERO")
	private Integer numero;

	@Column(name = "ENDERECO")
	private String endereco;

	@Column(name = "ESTADO")
	private String estado;

	@Column(name = "PAIS")
	private String pais;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "passaporte")
	private Turista turista;

	public Passaporte() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public String toString() {
		return "ID: " + this.id + " | ENDERECO: " + this.endereco + " | ESTADO: " + this.estado + " | PAIS: "
				+ this.pais + " | ID TURISTA: " + this.turista.getId();
	}

}