package restService;

import org.json.JSONObject;
import calculation.Calculation;

public class CalculationService {
	private Calculation calculation = new Calculation();
	private String storage = "";
	String operator = "";

	public String calculate(String request) {
		request = objectToString(request, "calculation");
		boolean negative = false;
		if (request.substring(0, 1).contentEquals("-")) {
			negative = true;
			request = request.substring(1, request.length());
		}
		operator = findOperator(request);
		return calculation.calculate(request, operator, negative);
	}

	public String findOperator(String request) {
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

	public String objectToString(String object, String value) {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
	
	public String store(String storage) {
		storage = objectToString(storage, "store");

		if(storage.substring(storage.length() - 1).contentEquals(".")) {
			storage = storage.substring(0, storage.length() - 1);
		}
		if(storage.contentEquals("error")) {
			storage = "";
		}
		return this.storage = storage;
	}

	public String getStorage() {
		return storage;
	}
}