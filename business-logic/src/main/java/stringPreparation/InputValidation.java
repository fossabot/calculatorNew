package stringPreparation;

public class InputValidation {

	public String checkInputValidity(String input) throws Exception {
		
		try {
			for (int i = 0; i <= input.length() - 1; i++) {
				checkChar(input, i);
			}
		}
		//TODO Stacktrace
		//TODO Frontend must not pass a null value to the backend
		catch (NullPointerException e) {
			throw new InvalidCharacterException();
		}
		return input;
	}

	public void checkChar(String input, int i) throws InvalidCharacterException {
		String validChars = "0123456789.+-/* ";
		
		String c =  Character.toString(input.charAt(i));
		
		if (!validChars.contains(c)) {
			throw new InvalidCharacterException();
			//TODO Exception-Handling
		}
	}
}