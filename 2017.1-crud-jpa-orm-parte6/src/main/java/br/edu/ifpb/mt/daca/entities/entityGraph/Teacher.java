package br.edu.ifpb.mt.daca.entities.entityGraph;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifpb.mt.daca.util.Util;

// @formatter:off
@Entity
@Table(name = "TB_TEACHER")
@NamedQueries({
		@NamedQuery(name = "Teacher.getAllFetchEverything",
					query = "SELECT distinct t FROM Teacher t " + 
							"left join fetch t.disciplines " + 
							"left join fetch t.specialties"),
		@NamedQuery(name = "Teacher.getAll", 
					query = "SELECT distinct t FROM Teacher t") })
@NamedEntityGraphs({ 
	@NamedEntityGraph(name = "graph.Teacher.everything",
					  attributeNodes = { @NamedAttributeNode(value = "disciplines", subgraph = "disciplines"),
							  			 @NamedAttributeNode(value = "specialties") 
				  					   },
				  	  subgraphs = { @NamedSubgraph(name = "disciplines", 
				  	  							   attributeNodes = { @NamedAttributeNode(value = "students"),
			  	  									   	 			  @NamedAttributeNode(value = "classes") 
				  	  							   }) 
				  	  }) 
	})
//@formatter:on
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "teacher")
	private Set<Discipline> disciplines;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "teacher")
	private Set<Specialty> specialties;

	public Teacher() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public Set<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Teacher other = (Teacher) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", " + "name=" + name + ", disciplines="
				+ Util.safeToStringLazyCollection(disciplines) + ", specialties="
				+ Util.safeToStringLazyCollection(specialties) + "]";
	}

}
