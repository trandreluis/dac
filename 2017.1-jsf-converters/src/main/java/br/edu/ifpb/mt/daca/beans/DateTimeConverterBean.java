package br.edu.ifpb.mt.daca.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class DateTimeConverterBean {

	private Date valor;

	public Date getValor() {
		return valor;
	}

	public void setValor(Date valor) {
		this.valor = valor;
	}
	
}
