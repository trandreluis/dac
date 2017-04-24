package br.edu.ifpb.dac.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class PaginaInicialBean {

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
