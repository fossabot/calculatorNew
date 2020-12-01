package exceptions;

public class StorageException extends Exception {

	public StorageException() {
		super("The input could not be stored.");
	}

	private static final long serialVersionUID = 2627675438217710034L;
}