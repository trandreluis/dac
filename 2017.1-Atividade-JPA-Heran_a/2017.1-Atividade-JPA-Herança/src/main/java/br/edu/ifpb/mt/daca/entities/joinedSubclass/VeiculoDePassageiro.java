package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VEICULO_PASSAGEIRO")
@Table(name = "JS_TB_VEICULO_PASSAGEIRO")
@DiscriminatorValue("VP")
public class VeiculoDePassageiro extends Veiculo {

	@Column(name = "MAX_PASSAGEIROS")
	private Integer numMaxPassageiros;

	public VeiculoDePassageiro() {

	}

	public Integer getNumMaxPassageiros() {
		return numMaxPassageiros;
	}

	public void setNumMaxPassageiros(Integer numMaxPassageiros) {
		this.numMaxPassageiros = numMaxPassageiros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numMaxPassageiros == null) ? 0 : numMaxPassageiros.hashCode());
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
		VeiculoDePassageiro other = (VeiculoDePassageiro) obj;
		if (numMaxPassageiros == null) {
			if (other.numMaxPassageiros != null)
				return false;
		} else if (!numMaxPassageiros.equals(other.numMaxPassageiros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VeiculoDePassageiro [numMaxPassageiros=" + numMaxPassageiros + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getConstrutora()=" + getConstrutora() + "]";
	}

}
