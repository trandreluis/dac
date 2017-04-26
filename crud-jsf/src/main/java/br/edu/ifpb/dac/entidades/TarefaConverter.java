package br.edu.ifpb.dac.entidades;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.dac.dao.TarefaDAO;

@FacesConverter(value = "tarefaConverter")
public class TarefaConverter implements Converter {

	private TarefaDAO daoTarefa = new TarefaDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		int id = Integer.parseInt(value);

		return daoTarefa.buscarPorID(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Integer id = ((Tarefa) value).getId();

		return (id != null) ? id.toString() : null;
	}

}

