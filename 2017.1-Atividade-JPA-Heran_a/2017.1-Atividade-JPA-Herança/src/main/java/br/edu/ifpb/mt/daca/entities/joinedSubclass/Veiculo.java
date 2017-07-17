package br.edu.ifpb.mt.daca.entities.joinedSubclass;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//TODO: Mapeamento de herança usando a estratégia JOINED
@Entity(name = "VEICULO_JS")
@Table(name = "JS_TB_VEICULO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("V")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "CONSTRUTORA", nullable = false)
	private String construtora;

	public Veiculo() {

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

	public String getConstrutora() {
		return construtora;
	}

	public void setConstrutora(String construtora) {
		this.construtora = construtora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((construtora == null) ? 0 : construtora.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (construtora == null) {
			if (other.construtora != null)
				return false;
		} else if (!construtora.equals(other.construtora))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", nome=" + nome + ", construtora=" + construtora + "]";
	}

}
