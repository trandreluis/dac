package br.edu.ifpb.mt.daca.beans;

import java.math.BigInteger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class BigIntegerConverterBean {

	private BigInteger valor;

	public BigInteger getValor() {
		return valor;
	}

	public void setValor(BigInteger valor) {
		this.valor = valor;
	}
	
}
