package br.edu.ifpb.mt.daca.scopes;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

@NoneScoped
@ManagedBean
public class NoneScopedBean {

	private Date date;

	public NoneScopedBean() {

	}
	
	@PostConstruct
	public void init() {
		date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
