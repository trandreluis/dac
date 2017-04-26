package br.edu.ifpb.mt.daca.scopes;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class ViewScopedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date date;

	public ViewScopedBean() {

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
