package fr.mines.controller.actions;

/**
 * When an action doesn't exist
 */
public class ActionNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ActionNotFoundException(String message) {
		super(message);
	}

}
