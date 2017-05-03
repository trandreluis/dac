package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.entities.NumeroTelefone;

@ViewScoped
@ManagedBean
public class NumeroTelefoneConverterBean {

	private NumeroTelefone valor;

	public NumeroTelefone getValor() {
		return valor;
	}

	public void setValor(NumeroTelefone valor) {
		this.valor = valor;
	}

}
