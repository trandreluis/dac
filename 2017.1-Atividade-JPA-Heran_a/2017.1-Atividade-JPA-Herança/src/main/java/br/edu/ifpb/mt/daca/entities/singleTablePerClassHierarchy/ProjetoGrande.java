package br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "PROJETO_GRANDE")
@DiscriminatorValue("PG")
public class ProjetoGrande extends Projeto {

	@Column(name = "ORCAMENTO")
	private Double orcamento;

	public ProjetoGrande() {

	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((orcamento == null) ? 0 : orcamento.hashCode());
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
		ProjetoGrande other = (ProjetoGrande) obj;
		if (orcamento == null) {
			if (other.orcamento != null)
				return false;
		} else if (!orcamento.equals(other.orcamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjetoGrande [orcamento=" + orcamento + ", getId()=" + getId() + ", getNome()=" + getNome() + "]";
	}

}
