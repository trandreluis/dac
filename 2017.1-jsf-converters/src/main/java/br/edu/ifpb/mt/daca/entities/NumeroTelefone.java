package br.edu.ifpb.mt.daca.entities;

/**
 * Referência: http://www.javabeat.net/using-converters-in-jsf/
 * 
 * @author jaindsonvs
 *
 */
public class NumeroTelefone {

	private Integer codigoPaís;
	
	private Integer codigoÁrea;
	
	private Long numero;

	public NumeroTelefone(Integer codigoPaís, Integer codigoÁrea, Long numero) {
		super();
		this.codigoPaís = codigoPaís;
		this.codigoÁrea = codigoÁrea;
		this.numero = numero;
	}

	public Integer getCodigoPaís() {
		return codigoPaís;
	}

	public void setCodigoPaís(Integer codigoPaís) {
		this.codigoPaís = codigoPaís;
	}

	public Integer getCodigoÁrea() {
		return codigoÁrea;
	}

	public void setCodigoÁrea(Integer codigoÁrea) {
		this.codigoÁrea = codigoÁrea;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
}
