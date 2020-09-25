package calculation;

import java.util.StringTokenizer;

public class CalculationService {
	RequestParser requestParser = new RequestParser();
	
	public String getResult(String request) {
		String result = requestParser.parse(request);
		return result;
	}
	
	public String calculate(String request, String operator, boolean isPreSign) {
		String result ="";
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
