package br.edu.ifpb.mt.daca.entities.tablePerConcreteClass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "EMPREGADO_INTEGRAL_TPC")
@Table(name = "TPC_TB_INTEGRAL")
public class EmpTempoIntegral extends Empregado {

	private Double salario;

	public EmpTempoIntegral() {

	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
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
		EmpTempoIntegral other = (EmpTempoIntegral) obj;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmpTempoIntegral [salario=" + salario + ", getId()=" + getId() + ", getNome()=" + getNome() + "]";
	}

}
