package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ShortConverterBean {

	private Short valor;

	public Short getValor() {
		return valor;
	}

	public void setValor(Short valor) {
		this.valor = valor;
	}
	
}
