package br.edu.ifpb.mt.daca.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

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

		return users.getByID(id);
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
