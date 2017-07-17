package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "CAMINHAO")
@Table(name = "JS_TB_CAMINHAO")
@DiscriminatorValue("CM")
public class Caminhao extends VeiculoDeTransporte {

	@Column(name = "NUM_CONTEINERS")
	private Integer numDeContainers;

	public Caminhao() {

	}

	public Integer getNumDeContainers() {
		return numDeContainers;
	}

	public void setNumDeContainers(Integer numDeContainers) {
		this.numDeContainers = numDeContainers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numDeContainers == null) ? 0 : numDeContainers.hashCode());
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
		Caminhao other = (Caminhao) obj;
		if (numDeContainers == null) {
			if (other.numDeContainers != null)
				return false;
		} else if (!numDeContainers.equals(other.numDeContainers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Caminhao [numDeContainers=" + numDeContainers + ", getCargaMaximaKg()=" + getCargaMaximaKg()
				+ ", getId()=" + getId() + ", getNome()=" + getNome() + ", getConstrutora()=" + getConstrutora() + "]";
	}

}
