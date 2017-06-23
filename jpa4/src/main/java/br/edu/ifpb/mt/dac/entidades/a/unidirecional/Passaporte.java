package br.edu.ifpb.mt.dac.entidades.a.unidirecional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PASSAPORTE_UNI")
@Table(name = "TB_PASSAPORTE")
public class Passaporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	private Integer numero;

	private String endereco;

	private String estado;

	private String pais;
	
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

	public String toString() {
		return "ID: " + this.id + " | ENDERECO: " + this.endereco + " | ESTADO: " + this.estado + " | PAIS: "
				+ this.pais;
	}

}