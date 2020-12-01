package exceptions;

public class InvalidCharacterException extends Exception {
	
	public InvalidCharacterException() {
		super("The input is not valid.");
	}

	private static final long serialVersionUID = 2627675438217710034L;
}