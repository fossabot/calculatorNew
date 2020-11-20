package stringPreparation;

public class stringPreparation {
	
	public String checkInputValidity(String input) {
		
		input = deleteBlanks(input);
		
		try {
			for (int i = 0; i <= input.length() - 1; i++) {
				checkCharValidity(input, i);
			}
		}
		catch(InvalidCharacterException e) {
			e.printStackTrace();
		}
		return input;			
	}
	
	public String deleteBlanks(String input) {
		input = input.replaceAll(" +","");
		return input;
	}
	
	public void checkCharValidity(String input, int i) throws InvalidCharacterException {
		String validChars = "0123456789.+-/* ";
		
		String c =  Character.toString(input.charAt(i));
		
		if (!validChars.contains(c)) {
			throw new InvalidCharacterException();
		}
	}
}