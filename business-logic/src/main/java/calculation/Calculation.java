package calculation;

import java.util.StringTokenizer;

public class Calculation {
	public String result;
	
	public String addition(String term) {
		StringTokenizer str = new StringTokenizer(term, "+");
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
	
		return Double.toString(one + two);
	}
	
	public String subtraction(String term) {
		StringTokenizer str = new StringTokenizer(term, "-");
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
	
		return Double.toString(one - two);
	}
	
	public String multiplication(String term) {
		StringTokenizer str = new StringTokenizer(term, "*");
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
	
		return Double.toString(one * two);
	}
	
	public String division(String term) {
		StringTokenizer str = new StringTokenizer(term, "/");
		double one = Double.parseDouble(str.nextToken());
		double two = Double.parseDouble(str.nextToken());
		
		if(two == 0) {
			return "error";
		}
		else {
			return Double.toString(one / two);
		}
	}
}