package stringPreparation;

public class InputCleanUp {
	
	public String clearInput(String input) {
		
		input = input.replaceAll(" +","");
		
		return input;
	}
	
}