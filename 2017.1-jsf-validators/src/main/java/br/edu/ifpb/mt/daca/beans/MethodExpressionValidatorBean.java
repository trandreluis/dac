package br.edu.ifpb.mt.daca.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@RequestScoped
@ManagedBean
public class MethodExpressionValidatorBean {

	private Long numImpar;

	public Long getNumImpar() {
		return numImpar;
	}

	public void setNumImpar(Long numImpar) {
		this.numImpar = numImpar;
	}

	public void validateEhImpar(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		Long num = (Long) value;
		String msg = "Erro! O valor deve ser ímpar.";
		if (num % 2 == 0) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}

	}
}
