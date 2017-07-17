package br.edu.ifpb.mt.daca.entities.singleTablePerClassHierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "PROJETO_PEQUENO")
@DiscriminatorValue("PP")
public class ProjetoPequeno extends Projeto {

	public ProjetoPequeno() {

	}

	@Override
	public String toString() {
		return "ProjetoPequeno [getId()=" + getId() + ", getNome()=" + getNome() + "]";
	}

}