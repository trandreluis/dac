package br.edu.ifpb.mt.daca.entities.tablePerConcreteClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//TODO: Mapeamento de herança usando a estratégia TABLE_PER_CLASS
@Entity(name = "EMPREGADO_TPC")
@Table(name = "TPC_TB_EMPREGADO")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Empregado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	public Empregado() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empregado other = (Empregado) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empregado [id=" + id + ", nome=" + nome + "]";
	}

}
