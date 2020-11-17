package stringPreparation;

public class InputValidation {

	public String checkInputValidity(String input) throws Exception {
		
		String validChars = "0123456789.+-/* ";
		
		try {
			for (int i = 0; i <= input.length() - 1; i++) {
				String c =  Character.toString(input.charAt(i));
				
				if (!validChars.contains(c)) {
					throw new InvalidCharacterException();
				}
			}
		}
		catch (NullPointerException e) {
			throw new InvalidCharacterException();
		}
		return input;
	}
}