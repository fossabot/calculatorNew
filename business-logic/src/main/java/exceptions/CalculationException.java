package exceptions;

public class CalculationException extends Exception {

	public CalculationException() {
		super("Error while calculating the input.");
	}
	
	private static final long serialVersionUID = 2627675438217710034L;
}