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

	/* TODO
	 * Middle Man: Method only delegates work to other methods
	 */
	public String parseAndCalculate(String input) throws Exception {
		String result;
		try {
			input = objectToString(input, "calculation");
			input = inputValidation.checkInputValidity(input);
			input = inputCleanUp.deleteBlanks(input);
			calculationObject = parser.parseInput(input);
		}
		catch (Exception e) {
			throw new ParserException();
		}
		
		try {
			result = calculationService.getResult(calculationObject);
		}
		catch (Exception e) {
			throw new CalculationException();
		}
		return result;
	}
	
	void setInputValidation(InputValidation inputValidation) {
		this.inputValidation = inputValidation;
	}

	void setInputCleanUp(InputCleanUp inputCleanUp) {
		this.inputCleanUp = inputCleanUp;
	}
	
	void setParserService(ParserService parserService) {
		this.parser = parserService;
	}
	
	void setCalculationService(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	public String objectToString(String object, String value) {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
}