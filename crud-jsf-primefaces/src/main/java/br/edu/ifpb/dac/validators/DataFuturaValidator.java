package br.edu.ifpb.dac.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dataFuturaValidator")
public class DataFuturaValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Date data = (Date) value;

		if (new Date().after(data)) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "A data realizada tem que ser do futuro"));
		}

	}

}