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
import stringPreparation.InputCleanUp;
import stringPreparation.InputValidation;
import stringPreparation.InvalidCharacterException;

import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParseAndCalculateServiceTest {
	
	//TODO Testmethode aufteilen
	//TODO Dependencies wegmocken
	//TODO ObjectToString-Methode testen
	
	private ParseAndCalculateService parseAndCalculateService = new ParseAndCalculateService();
	
	@Mock InputValidation inputValidation;
	@Mock InputCleanUp inputCleanUp;
	@Mock ParserService parserService;
	@Mock CalculationService calculationService;
	@Mock CalculationObject object;
	 
	@BeforeEach
	public void setMethods() {
		parseAndCalculateService.setInputValidation(inputValidation);
		parseAndCalculateService.setInputCleanUp(inputCleanUp);
		parseAndCalculateService.setParserService(parserService);
		parseAndCalculateService.setCalculationService(calculationService);
	}

	@Test
	public void parseAndCalculate() throws Exception {
		String input = "{\r\n" + "\"calculation\": \"6+3\"\r\n" + "}";
		String calculation = "6+3";
		String expectedResult = "9";

		Mockito.when(inputValidation.checkInputValidity(calculation)).thenReturn(calculation);
		Mockito.when(inputCleanUp.deleteBlanks(calculation)).thenReturn(calculation);
		Mockito.when(parserService.parseInput(calculation)).thenReturn(object);
		Mockito.when(calculationService.getResult(object)).thenReturn(expectedResult);
		
		String result = parseAndCalculateService.parseAndCalculate(input);
		
		verify(inputValidation, times(1)).checkInputValidity(calculation);
		verify(inputCleanUp, times(1)).deleteBlanks(calculation);
		verify(parserService, times(1)).parseInput(calculation);
		verify(calculationService, times(1)).getResult(object);
		
		Mockito.inOrder(inputValidation, inputCleanUp, parserService, calculationService);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void parseAndCalculateThrowsParserExceptionBecauseOfInvalidInput() throws Exception {
		String wrongInput = "{\r\n" + "\"calculation\": \"6%3\"\r\n" + "}";
		String wrongCalculation = "6%3";

		Mockito.when(inputValidation.checkInputValidity(wrongCalculation)).thenThrow(InvalidCharacterException.class);

		ParserException e = assertThrows(ParserException.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongInput);
		});
		
		assertEquals("Fehler beim Parsen der Eingabe.", e.getMessage());
		
		verify(inputValidation, times(1)).checkInputValidity(wrongCalculation);
	}
	
	@Test
	public void parseAndCalculateThrowsParserExceptionBecauseOfParserError() throws Exception {
		String wrongInput = "{\r\n" + "\"calculation\": \"6%3\"\r\n" + "}";
		String wrongCalculation = "6%3";
		
		Mockito.when(inputValidation.checkInputValidity(wrongCalculation)).thenReturn(wrongCalculation);
		Mockito.when(inputCleanUp.deleteBlanks(wrongCalculation)).thenReturn(wrongCalculation);

		Mockito.when(parserService.parseInput(wrongCalculation)).thenThrow(Exception.class);

		ParserException e = assertThrows(ParserException.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongInput);
		});
		assertEquals("Fehler beim Parsen der Eingabe.", e.getMessage());
		
		verify(inputValidation, times(1)).checkInputValidity(wrongCalculation);
		verify(inputCleanUp, times(1)).deleteBlanks(wrongCalculation);
		verify(parserService, times(1)).parseInput(wrongCalculation);
		
		Mockito.inOrder(inputValidation, inputCleanUp, parserService);
	}
	
	@Test
	public void parseAndCalculateThrowsCalculationExceptionBecauseOfCalculationError() throws Exception {
		String input = "{\r\n" + "\"calculation\": \"6/3\"\r\n" + "}";
		String calculation = "6/3";

		Mockito.when(inputValidation.checkInputValidity(calculation)).thenReturn(calculation);
		Mockito.when(inputCleanUp.deleteBlanks(calculation)).thenReturn(calculation);
		Mockito.when(parserService.parseInput(calculation)).thenReturn(object);
		Mockito.when(calculationService.getResult(object)).thenThrow(Exception.class);

		CalculationException e = assertThrows(CalculationException.class, () -> {
			parseAndCalculateService.parseAndCalculate(input);
		});
		assertEquals("Fehler beim Berechnen der Eingabe.", e.getMessage());
		
		verify(inputValidation, times(1)).checkInputValidity(calculation);
		verify(inputCleanUp, times(1)).deleteBlanks(calculation);
		verify(parserService, times(1)).parseInput(calculation);
		verify(calculationService, times(1)).getResult(object);
		
		Mockito.inOrder(inputValidation, inputCleanUp, parserService, calculationService);
	}
	
	@Test
	public void objectToString() {
		String value = "8+3";
		assertEquals(value, parseAndCalculateService.objectToString("{\r\n" + "\"calculation\": \"" + value + "\"\r\n" + "}", "calculation"));
	}
}