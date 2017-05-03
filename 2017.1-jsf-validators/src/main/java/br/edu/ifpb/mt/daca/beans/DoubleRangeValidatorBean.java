package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class DoubleRangeValidatorBean {

	private Double valorDouble;

	public Double getValorDouble() {
		return valorDouble;
	}

	public void setValorDouble(Double valorDouble) {
		this.valorDouble = valorDouble;
	}

}
