package calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import parser.CalculationObject;
import parser.ParserService;
import stringPreparation.StringPreparation;

import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import exceptions.CalculationException;
import exceptions.InvalidCharacterException;

@ExtendWith(MockitoExtension.class)
public class ParseAndCalculateServiceTest {
	
	private ParseAndCalculateService parseAndCalculateService = new ParseAndCalculateService();
	
	@Mock StringPreparation stringPreparation;
	@Mock ParserService parserService;
	@Mock CalculationService calculationService;
	@Mock CalculationObject object;
	 
	@BeforeEach
	public void setMethods() {
		parseAndCalculateService.setStringPreparation(stringPreparation);
		parseAndCalculateService.setParserService(parserService);
		parseAndCalculateService.setCalculationService(calculationService);
	}

	@Test
	public void parseAndCalculate() throws Exception {
		String input = "{\r\n" + "\"calculation\": \"6+3\"\r\n" + "}";
		String calculation = "6+3";
		String expectedResult = "9";

		Mockito.when(stringPreparation.checkInputValidity(calculation)).thenReturn(calculation);
		Mockito.when(parserService.parseInput(calculation)).thenReturn(object);
		Mockito.when(calculationService.getResult(object)).thenReturn(expectedResult);
		
		String result = parseAndCalculateService.parseAndCalculate(input);
		
		verify(stringPreparation, times(1)).checkInputValidity(calculation);
		verify(parserService, times(1)).parseInput(calculation);
		verify(calculationService, times(1)).getResult(object);
		
		Mockito.inOrder(stringPreparation, parserService, calculationService);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void parseAndCalculateThrowsCalculationExceptionBecauseOfInvalidInput() throws Exception {
		String wrongInput = "{\r\n" + "\"calculation\": \"6%3\"\r\n" + "}";
		String wrongCalculation = "6%3";

		Mockito.when(stringPreparation.checkInputValidity(wrongCalculation)).thenThrow(InvalidCharacterException.class);

		CalculationException e = assertThrows(CalculationException.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongInput);
		});
		
		assertEquals("Error while calculating the input.", e.getMessage());
		
		verify(stringPreparation, times(1)).checkInputValidity(wrongCalculation);
	}
	
	@Test
	public void parseAndCalculateThrowsCalculationExceptionBecauseOfParserError() throws Exception {
		String wrongInput = "{\r\n" + "\"calculation\": \"6%3\"\r\n" + "}";
		String wrongCalculation = "6%3";
		
		Mockito.when(stringPreparation.checkInputValidity(wrongCalculation)).thenReturn(wrongCalculation);
		Mockito.when(parserService.parseInput(wrongCalculation)).thenThrow(CalculationException.class);

		assertThrows(CalculationException.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongInput);
		});
		
		verify(stringPreparation, times(1)).checkInputValidity(wrongCalculation);
		verify(parserService, times(1)).parseInput(wrongCalculation);
		
		Mockito.inOrder(stringPreparation, parserService);
	}
	
	@Test
	public void parseAndCalculateThrowsCalculationExceptionBecauseOfCalculationError() throws Exception {
		String input = "{\r\n" + "\"calculation\": \"6/3\"\r\n" + "}";
		String calculation = "6/3";

		Mockito.when(stringPreparation.checkInputValidity(calculation)).thenReturn(calculation);
		Mockito.when(parserService.parseInput(calculation)).thenReturn(object);
		Mockito.when(calculationService.getResult(object)).thenThrow(CalculationException.class);
		
		assertThrows(CalculationException.class, () -> {
			parseAndCalculateService.parseAndCalculate(input);
		});
		
		verify(stringPreparation, times(1)).checkInputValidity(calculation);
		verify(parserService, times(1)).parseInput(calculation);
		verify(calculationService, times(1)).getResult(object);
		
		Mockito.inOrder(stringPreparation, parserService, calculationService);
	}
	
	@Test
	public void objectToString() {
		String value = "8+3";
		assertEquals(value, parseAndCalculateService.objectToString("{\r\n" + "\"calculation\": \"" + value + "\"\r\n" + "}", "calculation"));
	}
	
	@Test
	public void parseAndCalculateThrowsCalculationExceptionBecauseOfJSONError() throws Exception {
		String wrongJSONFormatInput = "{\r\n" + "\"\": \"6/3\"\r\\rn" + "}";
		
		assertThrows(CalculationException.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongJSONFormatInput);
		});
	}
}