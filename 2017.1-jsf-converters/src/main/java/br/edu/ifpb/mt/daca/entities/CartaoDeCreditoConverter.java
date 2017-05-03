package br.edu.ifpb.mt.daca.entities;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Referência: https://docs.oracle.com/javaee/7/tutorial/jsf-custom010.htm
 * 
 * @author jaindsonvs
 */
@FacesConverter(value = "cdcc")
public class CartaoDeCreditoConverter implements Converter {

	private Pattern pattern = Pattern.compile("\\d{16}|\\d{4} \\d{4} \\d{4} \\d{4}|\\d{4}-\\d{4}-\\d{4}-\\d{4}");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}

		// Verificar se a string está no formato esperado. Ex: 4532950121707173 | 4532 9501 2170 7173 | 4532-9501-2170-7173
		if (!pattern.matcher(value).matches()) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
		
		// Construir o objeto a partir da string
		String valorConvertido = value.replaceAll(" ", "").replaceAll("-", "");

		return valorConvertido;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof String)) {
			return null;
		}
		
		String valor = (String) value;
		
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("-", "");

		char[] input = valor.toCharArray();
        StringBuilder builder = new StringBuilder(input.length + 3);

        for (int i = 0; i < input.length; ++i) {
            if (((i % 4) == 0) && (i != 0)) {
                builder.append(" ");
            }
            builder.append(input[i]);
        }
		
		String valorConvertido = builder.toString();
		
		return valorConvertido;
	}

}
