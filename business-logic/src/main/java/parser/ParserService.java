package parser;

import java.util.ArrayList;

import exceptions.CalculationException;
import exceptions.InvalidCharacterException;

public class ParserService {
	
	boolean isOperator = false, isDot1 = false, isDot2 = false;
	
	public CalculationObject parseInput(String input) throws CalculationException {

		String operand1 = "", operand2 = "", operator = "";
		CalculationObject calculation = new CalculationObject();
		ArrayList<String> charsAsCategory = new ArrayList<String>();
		
		for (int i = 0; i <= input.length() - 1; i++) {
			
			try {
				charsAsCategory = identifyChar(input.charAt(i), charsAsCategory, i);
			}
			catch(InvalidCharacterException e) {
				e.printStackTrace();
				throw new CalculationException();
			}
			
			char currentChar = input.charAt(i);
			
			if(isOperand1(charsAsCategory.get(i), i)) {
				operand1 = extendParameter(operand1, currentChar);
			}
			
			else if(isOperator(charsAsCategory.get(i))) {
				operator =  Character.toString(currentChar);
			}
			
			else if(isOperand2(charsAsCategory.get(i), i)) {
				operand2 = extendParameter(operand2, currentChar);
			}
		}
		if(isOperator && operand1 != "" && operand2 != "") {
			calculation = new CalculationObject(operand1, operator, operand2);
		}
		else {
			throw new CalculationException();
		}
		return calculation;
	}
	
	public ArrayList<String> identifyChar(char character, ArrayList<String> chars, int index) throws InvalidCharacterException {
		
		final String VALID_NUMBERS = "0123456789";
		final String VALID_OPERATORS = "+-*/";
		String c = Character.toString(character);
		
		if (VALID_NUMBERS.contains(c)) {
			chars.add("number");
		}
		else if (c.equals(".")) {
			chars.add("dot");
		}
		else if (index == 0 && c.equals("-")) {
			chars.add("preSign");
		}
		else if (VALID_OPERATORS.contains(c)) {
			chars.add("operator");
		}
		else {
			throw new InvalidCharacterException();
		}
		return chars;
	}
	
	public boolean isOperand1(String currentChar, int i) throws CalculationException {
		boolean isOperand1 = false;
		
		if(!isOperator) {
			if(currentChar.equals("preSign") || currentChar.equals("number")) {
				isOperand1 = true;
			}
			else if(currentChar.equals("dot")) {
				isOperand1 = checkDotCorrectness(currentChar, i, isDot1);
				isDot1 = true;
			}
		}
		return isOperand1;	
	}
	
	public boolean isOperand2(String currentChar, int i) throws CalculationException {
		boolean isOperand2 = false;
		
		if(isOperator) {
			if(currentChar.equals("number")) {
				isOperand2 = true;
			}
			else if(currentChar.equals("dot")) {
				isOperand2 = checkDotCorrectness(currentChar, i, isDot2);
				isDot2 = true;
			}
			else {
				throw new CalculationException();
			}
		}
		return isOperand2;
	}
	
	public boolean isOperator(String currentChar) {
		boolean correctOperator = false;
		
		if(currentChar.equals("operator") && !isOperator) {
			correctOperator = true;
			isOperator = true;
		}
		return correctOperator;
	}
	
	public String extendParameter(String parameter, char c) {
		parameter = parameter + c;
		return parameter;
	}
	
	public boolean checkDotCorrectness(String currentChar, int i, boolean Dot) throws CalculationException {
		boolean isCorrect = false;
		
		if(!Dot) {
			if(i > 0) {
				isCorrect = true;
			}
		}
		else {
			throw new CalculationException();
		}
		return isCorrect;
	}
}