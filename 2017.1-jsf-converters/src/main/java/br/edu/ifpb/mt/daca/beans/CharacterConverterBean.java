package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class CharacterConverterBean {

	private Character valor;

	public Character getValor() {
		return valor;
	}

	public void setValor(Character valor) {
		this.valor = valor;
	}
	
}
