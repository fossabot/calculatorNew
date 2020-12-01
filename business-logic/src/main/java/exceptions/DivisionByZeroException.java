package exceptions;

public class DivisionByZeroException extends Exception {

	public DivisionByZeroException() {
		super("Error because division by zero.");
	}
	
	private static final long serialVersionUID = 2627675438217710034L;
}