package stringPreparation;

public class InvalidCharacterException extends Exception {
	
	InvalidCharacterException() {
		super("Die Eingabe ist nicht valide.");
	}
}