package calculation;

import org.json.JSONObject;

public class RequestParser {
	CalculationService calculationService = new CalculationService();
	
	//Error-Messages
	String errorMessageInvalidOperation = "Dies ist keine valide Rechenoperation";
	String errorMessageConcatenation = "Bitte keine Verkettung von Rechenoperationen";
	String errorMessageCalculationFailed = "Berechnung nicht möglich";
	
	String result = "";
	String operations  = "+-/*";
	String operator = "";

	
	//Getting the calculation-String from JSON
	public String parse(String request) {
		request = objectToString(request, "calculation");
		
		
		if (checkCharCorrectness(request)) { //Only valid inputs --> "0123456789.+-/* "
			request = removeBlankSpaces(request); //Removes all blank spaces
			request = checkDecimalPoints(request); //Only valid decimal-points
			
			if(request.contentEquals("wrong")) {
				//String contains multiple decimal-points in a row
				result = "error";
				System.out.println(errorMessageInvalidOperation);			
			}
			else {
				if (isOperator(request)) { //String is a calculation
					if(checkNumberOfOperations(request) > 1) {
						if (checkIfPreSign(request)) { //String contains pre-sign
							boolean isPreSign = true;
							request = request.substring(1, request.length());
							
							operator = getOperator(request);
							
							result = calculationService.calculate(request, operator, isPreSign);
						}
						/*
						else if (checkSignsInARow(request)) {
							result = "error";
							System.out.println(errorMessageInvalidOperation);
						}*/
 						else {
							//String contains multiple operation-signs
							result = "error";
							System.out.println(errorMessageConcatenation);
						}						
					}
					else { //String is a valid operation
						boolean isPreSign = false;
						operator = getOperator(request);
						result = calculationService.calculate(request, operator, isPreSign);
					}
				}	
				else { //String is not a calculation
					result = request;
				}
			}
		}
		else { //String contains unexpected operation sign, letters, brackets
			result = "error";
			System.out.println(errorMessageInvalidOperation);
		}
		return result;
	}
	
	public String objectToString(String object, String value) {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
	
	private boolean checkCharCorrectness(String request) {	
		boolean correctness = false;
		String validInputs = "0123456789.+-/* ";
		
		//Empty String ""
		if (request.isEmpty()) {
			correctness = true;
		}
		else {
			for (int i = 0; i <= request.length() - 1; i++) {
				if (validInputs.contains(request.charAt(i)+"")) {
					correctness = true;
				}
				//If one character is invalid, cancel the loop
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
	
	private String checkDecimalPoints(String request) {
		for (int i = 0; i <= request.length() - 1; i++) {
			if (request.charAt(i) == '.') {
				//If decimal-point in first place, add 0
				// ".9" --> "0.9"
				if (i == 0) {
					request = "0" + request;
				}
				//Check next index if index != request.length - 1
				else if (i < request.length() - 1) {
					char previousChar = request.charAt(i-1);
					char nextChar = request.charAt(i+1);
					
					// "2..2 --> invalid
					if (nextChar == '.' || previousChar == '.') {
						request = "wrong";
					}
					
					// "2.+2" --> "2+2"
					else if (!Character.isDigit(nextChar)) {
						request = request.substring(0, i)+request.substring(i+1);
					}
					
					// "2+.2" --> "2+0.2"
					else if (!Character.isDigit(previousChar)) {
						request = request.substring(0, i) + "0" + request.substring(i);
					}
				}
				else if (i == request.length() - 1) {
					request = request.substring(0, request.length() - 1);	
				}
			}
		}
		return request;
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
	
	/*private boolean checkSignsInARow(String request) {
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
	*/
}