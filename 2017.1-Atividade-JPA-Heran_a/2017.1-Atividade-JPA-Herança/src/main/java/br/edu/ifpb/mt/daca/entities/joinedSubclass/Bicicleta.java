package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "BICICLETA")
@Table(name = "JS_TB_BICICLETA")
@DiscriminatorValue("BI")
public class Bicicleta extends VeiculoDePassageiro {

	@Column(name = "ALTURA_SELIM_CM")
	private Integer alturaDoSelimCm;

	public Bicicleta() {

	}

	public Integer getAlturaDoSelimCm() {
		return alturaDoSelimCm;
	}

	public void setAlturaDoSelimCm(Integer alturaDoSelimCm) {
		this.alturaDoSelimCm = alturaDoSelimCm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alturaDoSelimCm == null) ? 0 : alturaDoSelimCm.hashCode());
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
		Bicicleta other = (Bicicleta) obj;
		if (alturaDoSelimCm == null) {
			if (other.alturaDoSelimCm != null)
				return false;
		} else if (!alturaDoSelimCm.equals(other.alturaDoSelimCm))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bicicleta [alturaDoSelimCm=" + alturaDoSelimCm + ", getNumMaxPassageiros()=" + getNumMaxPassageiros()
				+ ", getId()=" + getId() + ", getNome()=" + getNome() + ", getConstrutora()=" + getConstrutora() + "]";
	}
}
