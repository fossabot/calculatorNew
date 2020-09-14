package restService;

import org.json.JSONObject;
import calculation.Calculation;

public class CalculationService {
	private Calculation calculation = new Calculation();
	private String storage = "";
	
	public String seperate(String request) {
		String operator = "";
		
		boolean isAddition = (request.indexOf("+")>0);
		if (isAddition) {
			operator = "+";
		}
		
		boolean isSubtraction = (request.indexOf("-")>0);
		if (isSubtraction) {
			operator = "-";
		}
		
		boolean isMultiplication = (request.indexOf("*")>0);
		if (isMultiplication) {
			operator = "*";
		}

		boolean isDivision = (request.indexOf("/")>0);
		if (isDivision) {
			operator = "/";
		}
		return chooseOperation(request, operator);
	}
	
	public String chooseOperation(String request, String operator) {
		JSONObject obj =  new JSONObject(request);
		String term = obj.getString("calculation");
		String result = "";
		
		switch (operator) {
			case "+":
			  result = calculation.addition(term);
			  break;
			case "-":
			  result = calculation.subtraction(term);
			  break;
			case "*":
			  result = calculation.multiplication(term);
			  break;
			case "/":
			  result = calculation.division(term);
			  break;
		}
		return result;
	}
	
	public void store(String storage) {
		this.storage = storage;
	}

	public String getStorage() {
		JSONObject obj =  new JSONObject(storage);
		String store = obj.getString("store");
		return store;
	}
}