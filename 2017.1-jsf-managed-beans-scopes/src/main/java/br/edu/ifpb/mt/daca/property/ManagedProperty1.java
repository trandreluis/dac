package br.edu.ifpb.mt.daca.property;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class ManagedProperty1 {

	@ManagedProperty(value="#{managedProperty2}")
	private ManagedProperty2 managedProperty2;
	
	@ManagedProperty(value="LOL")
	private String valor2;
	
	public ManagedProperty1() {
		
	}

	public ManagedProperty2 getManagedProperty2() {
		return managedProperty2;
	}

	public void setManagedProperty2(ManagedProperty2 managedProperty2) {
		this.managedProperty2 = managedProperty2;
	}
	
	public String getValor() {
		return this.managedProperty2.getValor();
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}
}
