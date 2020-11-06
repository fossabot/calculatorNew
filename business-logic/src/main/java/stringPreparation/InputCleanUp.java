package stringPreparation;

public class InputCleanUp {
	
	public String deleteBlanks(String input) {
		input = input.replaceAll(" +","");
		return input;
	}
}