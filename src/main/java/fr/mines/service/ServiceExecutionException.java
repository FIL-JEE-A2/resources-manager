package fr.mines.service;

/**
 * Exception that happen when a service fail
 */
public class ServiceExecutionException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceExecutionException(String message) {
		super(message);
	}

}
