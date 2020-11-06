package stringPreparation;

public class InputValidation {
	
	public String checkInputValidity(String input) throws Exception {
		
		String validChars = "0123456789.+-/* ";
		
		for (int i = 0; i <= input.length() - 1; i++) {
			
			String c =  Character.toString(input.charAt(i));
			
			if (!validChars.contains(c)) {
				throw new Exception("Die Eingabe ist nicht valide.");
			}
		}
		return input;
	}
}