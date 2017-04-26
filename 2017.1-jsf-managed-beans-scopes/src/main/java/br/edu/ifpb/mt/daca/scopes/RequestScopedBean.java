package br.edu.ifpb.mt.daca.scopes;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class RequestScopedBean {

	private Date date;
	
	public RequestScopedBean() {
		
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
