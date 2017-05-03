package br.edu.ifpb.mt.daca.entities;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Referência: http://www.javabeat.net/using-converters-in-jsf/
 * 
 * @author jaindsonvs
 */
@FacesConverter(forClass = NumeroTelefone.class)
public class NumeroTelefoneConverter implements Converter {

	private Pattern pattern = Pattern.compile("\\d+-\\d+-\\d+");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}

		// Verificar se a string está no formato esperado. Ex: 55-83-12345678
		if (!pattern.matcher(value).matches()) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}

		// Construir o objeto a partir da string
		StringTokenizer hyphenTokenizer = new StringTokenizer(value, "-");

		Integer codigoPaís = Integer.parseInt(hyphenTokenizer.nextToken());
		Integer codigoÁrea = Integer.parseInt(hyphenTokenizer.nextToken());
		Long numero = Long.parseLong(hyphenTokenizer.nextToken());

		return new NumeroTelefone(codigoPaís, codigoÁrea, numero);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (!(value instanceof NumeroTelefone)) {
			return null;
		}

		NumeroTelefone numeroTelefone = (NumeroTelefone) value;

		return numeroTelefone.getCodigoPaís() + "-" + numeroTelefone.getCodigoÁrea() + "-" + numeroTelefone.getNumero();
	}

}
