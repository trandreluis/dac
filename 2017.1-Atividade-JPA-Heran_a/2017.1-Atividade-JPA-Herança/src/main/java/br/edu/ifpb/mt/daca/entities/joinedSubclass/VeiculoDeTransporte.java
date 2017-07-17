package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VEICULO_TRANSPORTE")
@Table(name = "JS_TB_TRANSPORTE")
@DiscriminatorValue("VT")
public class VeiculoDeTransporte extends Veiculo {

	@Column(name = "CARGA_MAX_KG")
	private Integer cargaMaximaKg;

	public VeiculoDeTransporte() {

	}

	public Integer getCargaMaximaKg() {
		return cargaMaximaKg;
	}

	public void setCargaMaximaKg(Integer cargaMaximaKg) {
		this.cargaMaximaKg = cargaMaximaKg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargaMaximaKg == null) ? 0 : cargaMaximaKg.hashCode());
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
		VeiculoDeTransporte other = (VeiculoDeTransporte) obj;
		if (cargaMaximaKg == null) {
			if (other.cargaMaximaKg != null)
				return false;
		} else if (!cargaMaximaKg.equals(other.cargaMaximaKg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VeiculoDeTransporte [cargaMaximaKg=" + cargaMaximaKg + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getConstrutora()=" + getConstrutora() + "]";
	}
}
