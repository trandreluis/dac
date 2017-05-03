package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.mt.daca.entities.MyEnum;

@ViewScoped
@ManagedBean
public class EnumConverterBean {

	private MyEnum valor;

	public MyEnum getValor() {
		return valor;
	}

	public void setValor(MyEnum valor) {
		this.valor = valor;
	}
	
}
