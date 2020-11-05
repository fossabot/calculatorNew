package calculation;
import org.json.JSONObject;

import parser.CalculationObject;
import parser.ParserService;
import stringPreparation.InputCleanUp;
import stringPreparation.InputValidation;

public class ParseAndCalculateService {
	
	private InputValidation inputValidation = new InputValidation();
	private InputCleanUp inputCleanUp = new InputCleanUp();
	private ParserService parser = new ParserService();
	private CalculationService calculationService = new CalculationService();
	private CalculationObject calculationObject = new CalculationObject();

	public String parseAndCalculate(String input) throws Exception {
		input = objectToString(input, "calculation");
		input = inputValidation.validateInput(input);
		input = inputCleanUp.clearInput(input);
		
		calculationObject = parser.parseInput(input);
		String result = calculationService.getResult(calculationObject);
		
		return result;
	}
	
	public String objectToString(String object, String value) {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
}