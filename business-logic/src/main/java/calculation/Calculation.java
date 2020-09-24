package calculation;

import java.util.StringTokenizer;

public class Calculation {
	//Error-Messages
	String errorMessageInvalidOperation = "Dies ist keine valide Rechenoperation";
	String errorMessageConcatenation = "Bitte keine Verkettung von Rechenoperationen";
	String errorMessageCalculationFailed = "Berechnung nicht möglich";

	String result = "";
	String operations  = "+-/*";
	String operator = "";
	
	public String calculateRequest(String request) {
		
		if (checkCharCorrectness(request)) { //Only valid inputs --> "0123456789.+-/* "
			removeBlankSpaces(request);
			
			if(checkDecimalPoints(request)) { //Only valid decimal-points
				if(request.charAt(0) == '.') {
					request = "0" + request;
				}
				
				if (isOperator(request)) { //String is a calculation
					
					if(checkNumberOfOperations(request) > 1) {
						
						if (checkIfPreSign(request)) { //String contains pre-sign
							boolean isPreSign = true;
							request = request.substring(1, request.length());
							operator = getOperator(request);
							result = calculate(request, operator, isPreSign);
						}
						else if (checkSingsInARow(request)) {
							result = "error";
							System.out.println(errorMessageInvalidOperation);
						}
 						else {
							//String contains multiple operation-signs
							result = "error";
							System.out.println(errorMessageConcatenation);
						}
					}
					else { //String is a valid operation
						boolean isPreSign = false;
						operator = getOperator(request);
						result = calculate(request, operator, isPreSign);
					}
				}	
				else { //String is not a calculation
					result = request;
				}
			}
			else {
				//String contains multiple decimal-points in a row
				result = "error";
				System.out.println(errorMessageInvalidOperation);
			}
		}
		else { //String contains unexpected operation sign, letters, brackets
			result = "error";
			System.out.println(errorMessageInvalidOperation);
		}
		return result;
	}
	
	private boolean checkCharCorrectness(String request) {	
		boolean correctness = false;
		String validInputs = "0123456789.+-/* ";
		
		if (request.isEmpty()) {
			correctness = true;
		}
		else {
			for (int i = 0; i <= request.length() - 1; i++) {
				if (validInputs.contains(request.charAt(i)+"")) {
					correctness = true;
				}
				else {
					i = request.length();
					correctness = false;
				}
			}
		}
		return correctness;
	}
	
	public String removeBlankSpaces(String request) {
		request = request.replaceAll(" +","");
		return request;
	}
	
	private boolean checkDecimalPoints(String request) {
		boolean ok = true;
		char charBefore = ' ';
		
		for (int i = 0; i <= request.length() - 1; i++) {
			if (request.charAt(i) == '.') {
				if (charBefore == '.') {
					ok = false;
				}
				else if(i > 0 && (!Character.isDigit(charBefore)|| !Character.isDigit(request.charAt(i+1)))) {
					ok = false;
				}
			}
			charBefore = request.charAt(i);
		}
		return ok;
	}
	
	private boolean checkSingsInARow(String request) {
		char charBefore = ' ';
		boolean isInARow = false;
		
		for (int i = 0; i <= request.length() - 1; i++) {
			if(charBefore == request.charAt(i)) {
				isInARow = true;
			}
			charBefore = request.charAt(i);
		}
		return isInARow;
	}

	private boolean isOperator(String request) {
		boolean isOperator = false;
		
		for (int i = 0; i < request.length(); i++) {
			if (operations.contains(request.charAt(i)+"")) {
				isOperator = true;
			}
		}
		return isOperator;
	}

	private String getOperator(String request) {
		for (int i = 0; i <= request.length(); i++) {
			if(request.indexOf("+") > 0) {
				operator = "+";
				break;
			}
			if(request.indexOf("-") > 0) {
				operator = "-";
				break;
			}
			if(request.indexOf("*") > 0) {
				operator = "*";
				break;
			}
			if(request.indexOf("/") > 0) {
				operator = "/";
				break;
			}
		}	
		return operator;
	}
	
	public int checkNumberOfOperations(String request) {
		int number = 0;
		
		for (int i = 0; i <= request.length() - 1; i++) {
			if(operations.contains(request.charAt(i)+"")) {
				number++;
			}
		}
		return number;
	}
	
	public boolean checkIfPreSign(String request) {
		boolean isPreSign = false;
		if (request.substring(0, 1).contentEquals("-")) {
			isPreSign = true;
		}
		return isPreSign;
	}
	
	public String calculate(String request, String operator, boolean isPreSign) {
		StringTokenizer str = new StringTokenizer(request, operator);
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
		double output = 0.0;
		
		if(isPreSign) {
			one *= -1;
		}
		
		switch (operator) {
			case "+":
				output = one + two;
				result = outputToString(output);
				break;
			case "-":
				output = one - two;
				result = outputToString(output);
				break;
			case "*":
				output = one * two;
				result = outputToString(output);
				break;
			case "/":
				try {
					output = one / two;
					if(output == Double.POSITIVE_INFINITY || output == Double.NEGATIVE_INFINITY) {
						throw new ArithmeticException();
					}
					else {
						result = outputToString(output);
					}
				}
				catch (ArithmeticException e) {
					System.out.println("Fehler: Division durch 0 nicht möglich");
					result = "error";
				}
				break;
		}  
		return result;
	}
	
	public String outputToString(double output) {
		String string = null;
		
		int value = (int) output;

		if (output != value) {
			string = Double.toString(output);
		}
		else {
			string = Integer.toString(value);
		}
		return string;
	}
}