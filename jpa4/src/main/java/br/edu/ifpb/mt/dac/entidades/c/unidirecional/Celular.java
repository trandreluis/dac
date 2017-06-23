package br.edu.ifpb.mt.dac.entidades.c.unidirecional;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "CELULAR_UNI")
@Table(name = "TB_CELULAR")
public class Celular {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CODIGO_PAIS")
	private Integer codigoPais;

	@Column(name = "DDD")
	private Integer ddd;

	@Column(name = "NUMERO")
	private Integer numero;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "CHAMADA_FK")
	private List<Chamada> chamadas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Chamada> getChamadas() {
		return chamadas;
	}

	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public String toString() {
		return "ID: " + this.id + " | CÓD. PAÍS: " + this.codigoPais + " | DDD: " + this.ddd + " | NÚMERO: "
				+ this.numero + " | QTD. CHAMADAS: " + this.chamadas.size();
	}

}