package br.edu.ifpb.mt.daca.entities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.mt.daca.dao.UserDAO;

/**
 * Referência: http://www.mastertheboss.com/javaee/jsf/develop-custom-jsf-converters
 * 
 * @author jaindsonvs
 */
@FacesConverter(forClass = User.class)
public class UserConverter implements Converter {

	private UserDAO userDAO = new UserDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}

		try {
			User user = userDAO.getByID(Integer.valueOf(value));
			// OBS: Qualquer exceção lançada por #getByID(...) deveria ser tratada também no try/catch
			return user;
		} catch (NumberFormatException nfe) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (!(value instanceof User)) {
			return null;
		}

		User user = (User) value;
		return String.valueOf(user.getId());
	}

}
