package applicationRestAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import calculation.ParseAndCalculateService;
import exceptions.CalculationException;
import exceptions.DivisionByZeroException;
import exceptions.InvalidCharacterException;
import exceptions.StorageException;
import storage.StorageService;

public class CalculatorControllerTest {

	private CalculatorController calculatorController = new CalculatorController();
	
	@Test
	public void testCalculateHappyPath_returns200() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/2\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenReturn("2");
		ResponseEntity<?> response = calculatorController.calculate(request);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"result\": \"2\"}", response.getBody());
	}

	@Test
	public void testCalculateNegativePath_returns406BecauseOfCalculationException() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/2a\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenThrow(CalculationException.class);
	    
		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Input cannot be calculated.";
		
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
		assertEquals(message, response.getBody());
	}
	
	@Test
	public void testCalculateNegativePath_returns406BecauseOfInvalidCharacterException() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/2a\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenThrow(InvalidCharacterException.class);

		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Input is invalid.";
		
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
		assertEquals(message, response.getBody());
	}
	
	@Test
	public void testCalculateNegativePath_returns403BecauseOfDivisionByZeroException() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/0\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenThrow(DivisionByZeroException.class);

		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Division by 0 is not allowed.";
		
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		assertEquals(message, response.getBody());
	}
	
	@Test
	public void testStoreHappyPath_returns200() throws Exception {		
		StorageService storageService = Mockito.mock(StorageService.class);
		String request = "{\"storage\": \"4\"}";
		calculatorController.setStorageService(storageService);
	
		Mockito.when(storageService.store(request)).thenReturn("4");
		ResponseEntity<?> response = calculatorController.store(request);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"result\": \"4\"}", response.getBody());
	}

	@Test
	public void testStoreNegativePath_returns406BecauseOfStorageException() throws Exception {
		StorageService storageService = Mockito.mock(StorageService.class);
		String request = "{\"calculation\": \"4\"}";
		calculatorController.setStorageService(storageService);
	
		Mockito.when(storageService.store(request)).thenThrow(StorageException.class);
		ResponseEntity<?> response = calculatorController.store(request);
		
		String message = "Input cannot be saved.";
		
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
		assertEquals(message, response.getBody());
	}
	
	@Test
	public void testGetStorageHappyPath() throws Exception {
		StorageService storageService = Mockito.mock(StorageService.class);
		calculatorController.setStorageService(storageService);
		
		Mockito.when(storageService.getStorage()).thenReturn("4");
		String storage = calculatorController.getStore();
		
		assertEquals("{\"result\": \"4\"}", storage);
	}
}