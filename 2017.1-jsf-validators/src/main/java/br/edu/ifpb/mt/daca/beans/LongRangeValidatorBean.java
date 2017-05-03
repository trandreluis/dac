package br.edu.ifpb.mt.daca.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class LongRangeValidatorBean {

	private Long valorLong;

	public Long getValorLong() {
		return valorLong;
	}

	public void setValorLong(Long valorLong) {
		this.valorLong = valorLong;
	}

}
