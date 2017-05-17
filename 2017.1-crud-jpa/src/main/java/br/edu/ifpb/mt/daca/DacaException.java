package br.edu.ifpb.mt.daca;

public class DacaException extends Exception {

	private static final long serialVersionUID = -7669751088704144947L;

	public DacaException(String message) {
		super(message);
	}

	public DacaException(String message, Throwable cause) {
		super(message, cause);
	}

}
