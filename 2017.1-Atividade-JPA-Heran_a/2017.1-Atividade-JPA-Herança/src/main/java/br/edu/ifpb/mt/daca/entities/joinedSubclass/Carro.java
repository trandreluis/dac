package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "CARRO")
@Table(name = "JS_TB_CARRO")
@DiscriminatorValue("CA")
public class Carro extends VeiculoDePassageiro {

	@Column(name = "NUM_PORTAS")
	private Integer numDePortas;

	public Carro() {

	}

	public Integer getNumDePortas() {
		return numDePortas;
	}

	public void setNumDePortas(Integer numDePortas) {
		this.numDePortas = numDePortas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numDePortas == null) ? 0 : numDePortas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (numDePortas == null) {
			if (other.numDePortas != null)
				return false;
		} else if (!numDePortas.equals(other.numDePortas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carro [numDePortas=" + numDePortas + ", getNumMaxPassageiros()=" + getNumMaxPassageiros()
				+ ", getId()=" + getId() + ", getNome()=" + getNome() + ", getConstrutora()=" + getConstrutora() + "]";
	}

}
