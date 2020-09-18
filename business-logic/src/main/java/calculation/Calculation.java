package calculation;

import java.util.StringTokenizer;

public class Calculation {
	public String calculate(String request, String operator, boolean negative) {
		StringTokenizer str = new StringTokenizer(request, operator);
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
		double result = 0;
		
		if(negative) {
			one *= -1;
		}
		
		switch (operator) {
			case "+":
				result = one + two;
				break;
			case "-":
				result = one - two;
				break;
			case "*":
				result = one * two;
				break;
			case "/":
				if(two == 0) {
					return "error";
				}
				else {
					result = one / two;
				}
				break;
		}
		int value = (int) result;

		if (result != value) {
			return Double.toString(result);
		}
		else {
			return Integer.toString(value);
		}
	}
}