package restService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorService {
	public String storeValue;

	public CalculatorService(String storeValue) {
		this.storeValue = storeValue;
	}
	/*
	public String eval(CalculationRequest request) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		String result = "";
		try {
			result =  engine.eval(request.calculation).toString();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
}