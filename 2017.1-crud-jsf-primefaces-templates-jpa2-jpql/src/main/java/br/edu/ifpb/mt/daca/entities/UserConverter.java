package br.edu.ifpb.mt.daca.entities;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.edu.ifpb.mt.daca.dao.PersistenciaDacaException;
import br.edu.ifpb.mt.daca.dao.UserDAO;

@ManagedBean
public class UserConverter implements Converter {

	private UserDAO users = new UserDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		int id = Integer.parseInt(value);

		try {
			return users.getByID(id);
		} catch (PersistenciaDacaException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro, e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Integer id = ((User) value).getId();

		return (id != null) ? id.toString() : null;
	}

}
