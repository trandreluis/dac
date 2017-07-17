package br.edu.ifpb.mt.daca.dao;

import br.edu.ifpb.mt.daca.DacaException;

public class PersistenciaDacaException extends DacaException {

	private static final long serialVersionUID = 7159282553688713660L;

	public PersistenciaDacaException(String message) {
		super(message);
	}

	public PersistenciaDacaException(String message, Throwable cause) {
		super(message, cause);
	}

}
