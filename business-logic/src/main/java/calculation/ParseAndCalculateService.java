package calculation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import exceptions.CalculationException;
import exceptions.DivisionByZeroException;
import exceptions.InvalidCharacterException;
import parser.CalculationObject;
import parser.ParserService;
import stringPreparation.StringPreparation;

public class ParseAndCalculateService {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private StringPreparation stringPreparation = new StringPreparation();
	private ParserService parser = new ParserService();
	private CalculationService calculationService = new CalculationService();
	private CalculationObject calculationObject = new CalculationObject();

	/* TODO
	 * Middle Man: Method only delegates work to other methods
	 */
	public String parseAndCalculate(String input) throws CalculationException, DivisionByZeroException {
		String result;
		try {
			input = objectToString(input, "calculation");
			input = stringPreparation.checkInputValidity(input);
			calculationObject = parser.parseInput(input);
			result = calculationService.getResult(calculationObject);
		}
		catch(JSONException jsonE) {
			jsonE.printStackTrace();
			LOGGER.error(jsonE);
			throw new CalculationException();
		}
		catch(InvalidCharacterException e) {
			e.printStackTrace();
			LOGGER.error("Character is not valid.", e);
			throw new CalculationException();
		}
		return result;
	}

	void setStringPreparation(StringPreparation stringPreparation) {
		this.stringPreparation = stringPreparation;
	}
	
	void setParserService(ParserService parserService) {
		this.parser = parserService;
	}
	
	void setCalculationService(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	public String objectToString(String object, String value) throws JSONException {
		JSONObject obj =  new JSONObject(object);
		return obj.getString(value);
	}
}