package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ByteConverterBean {

	private Byte valor;

	public Byte getValor() {
		return valor;
	}

	public void setValor(Byte valor) {
		this.valor = valor;
	}
	
}
