package stringPreparation;

import exceptions.InvalidCharacterException;

public class StringPreparation {
	
	public String checkInputValidity(String input) throws InvalidCharacterException {
		try {
			input = deleteBlanks(input);
		}
		catch (NullPointerException e) {
			throw new InvalidCharacterException();
		}
		
		for (int i = 0; i <= input.length() - 1; i++) {
			checkCharValidity(input, i);
		}
		return input;
	}
	
	public String deleteBlanks(String input) throws NullPointerException {
		input = input.replaceAll(" +","");
		return input;
	}
	
	public void checkCharValidity(String input, int i) throws InvalidCharacterException {
		final String VALID_CHARS = "0123456789.+-/* ";
		String c =  Character.toString(input.charAt(i));
		
		if (!VALID_CHARS.contains(c)) {
			throw new InvalidCharacterException();
		}
	}
}