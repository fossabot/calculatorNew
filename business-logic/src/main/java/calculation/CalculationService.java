package calculation;

import parser.CalculationObject;

public class CalculationService {
	
	public String getResult(CalculationObject calculationObject) throws Exception {
		
		double result = 0;
		double operand1 = Double.parseDouble(calculationObject.getOperand1());
		double operand2 = Double.parseDouble(calculationObject.getOperand2());
		String operator = calculationObject.getOperator();

		switch(operator) {
		case "+":
			result = operand1 + operand2;
			break;
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			try {
				result = operand1 / operand2;
				if(result ==  Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {
					throw new ArithmeticException();
				}
			}
			catch (ArithmeticException e) {
				throw new Exception("Fehler: Division durch 0 nicht möglich");
			}
			break;
		}
		String returnResult = outputToString(result);
		
		return returnResult;
	}
		
	private String outputToString(double output) {
		String string = "";
		
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