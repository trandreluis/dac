package br.edu.ifpb.mt.daca.entities.tablePerConcreteClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "EMPREGADO_PARCIAL_TPC")
@Table(name = "TPC_TB_PARCIAL")
public class EmpTempoParcial extends Empregado {

	private Double valorHora;

	public EmpTempoParcial() {

	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((valorHora == null) ? 0 : valorHora.hashCode());
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
		EmpTempoParcial other = (EmpTempoParcial) obj;
		if (valorHora == null) {
			if (other.valorHora != null)
				return false;
		} else if (!valorHora.equals(other.valorHora))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmpTempoParcial [valorHora=" + valorHora + ", getId()=" + getId() + ", getNome()=" + getNome() + "]";
	}

}
