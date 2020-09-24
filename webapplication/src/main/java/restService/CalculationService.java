package restService;

import org.json.JSONObject;
import calculation.Calculation;

public class CalculationService {
	private Calculation calculation = new Calculation();

	public String calculate(String request) {
		request = objectToString(request, "calculation");
		return calculation.calculateRequest(request);
	}

	public String objectToString(String object, String value) {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
}