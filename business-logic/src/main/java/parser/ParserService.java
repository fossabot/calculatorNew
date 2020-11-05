package parser;

import java.util.ArrayList;

public class ParserService {
	
	String errorMessage = "Keine valide Rechenoperation";
	
	public CalculationObject parseInput(String input) throws Exception {
	
		ArrayList<String> chars = new ArrayList<String>();
		
		String operand1 = "";
		String operand2 = "";
		String operator = "";
		
		boolean isOperator = false;
		boolean isDot1 = false;
		boolean isDot2 = false;
		
		CalculationObject calculation = new CalculationObject();
		
		for (int i = 0; i <= input.length() - 1; i++) {
			
			chars = identifyChar(input.charAt(i), chars, i); //Classify value/char in category
			
			if (checkIfOperand1(chars) && !isOperator) {
				
				if(chars.get(i).equals("preSign")) {
					operand1 = operand1 + Character.toString(input.charAt(i));
				}
				else if (chars.get(i).equals("number")) {
					operand1 = operand1 + Character.toString(input.charAt(i));
				}
				else if (chars.get(i).equals("dot")) {
					if(isDot1) {
						throw new Exception("Keine valide Eingabe");
					}
					else {
						operand1 = operand1 + Character.toString(input.charAt(i));
						isDot1 = true;
					}
				}
				else if (checkIfOperator(chars.get(i))) {
					isOperator = true;
					operator =  Character.toString(input.charAt(i));
				}
			}
			
			else if (checkIfOperand2(chars.get(i)) && isOperator) {
		
				if (chars.get(i).equals("number")) {
					operand2 = operand2 + Character.toString(input.charAt(i));
				}
				else if (chars.get(i).equals("dot")) {
					if(isDot2) {
						throw new Exception("Keine valide Eingabe");
					}
					else {
						operand2 = operand2 + Character.toString(input.charAt(i));
						isDot2 = true;
					}
				}
			}
		}

		if(isOperator) {
			calculation = new CalculationObject(operand1, operator, operand2);
		}
		else {
			throw new Exception("Keine Rechenoperation");
		}
		
		return calculation;
	}

	public ArrayList<String> identifyChar(char character, ArrayList<String> chars, int index) throws Exception {
		
		String number = "number";
		String operator = "operator";
		String dot = "dot";
		String preSign = "preSign";

		String validNumbers = "0123456789";
		String validOperators = "+-*/";

		String c = Character.toString(character);
		
		if (validNumbers.contains(c)) {
			chars.add(number);
		}
		else if (c.equals(".")) {
			chars.add(dot);
		}
		else if (index == 0 && c.equals("-")) {
			chars.add(preSign);
		}
		else if (validOperators.contains(c)) {
			chars.add(operator);
		}
		else {
			throw new Exception("Parser-Error: Zeichen kann nicht identifiziert werden");
		}
		return chars;
	}

	public boolean checkIfOperand1(ArrayList<String> chars) throws Exception {
		
		String first = chars.get(0);
		boolean operand = true;
		
		if (first.equals("operator") || first.equals("dot")) {
			operand = false;
			throw new Exception(errorMessage);
		}
		return operand;
	}

	public boolean checkIfOperator(String string) throws Exception {
		
		boolean operator = false;
		
		if (string.equals("operator")) {
			operator = true;
		}
		else {
			throw new Exception(errorMessage);
		}
		return operator;
	}

	public boolean checkIfOperand2(String string) throws Exception {
		boolean operand = false;
		
		if (string.equals("number")) { 
			operand = true;
		}
		else if (string.equals("dot")) {
			operand = true;
		}
		else {
			throw new Exception(errorMessage);
		}
		return operand;
	}
}