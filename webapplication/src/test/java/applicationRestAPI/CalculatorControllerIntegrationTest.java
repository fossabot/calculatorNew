package applicationRestAPI;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorControllerIntegrationTest {

	private CalculatorController calculatorController = new CalculatorController();

	@Test
	public void testCalculateEndpointHappyPath() throws Exception {
		String request = "{\"calculation\": \"4/2\"}";
		ResponseEntity<?> response = calculatorController.calculate(request);
		assertEquals("{\"result\": \"2\"}", response.getBody());
	}
	
	@Test
	public void testCalculateEndpointNegativePathCalculationException() throws Exception {
		String request = "{\"calculation\": \"4/2a\"}";
		
		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Input cannot be calculated.";
		
	    assertEquals(message, response.getBody());
	}
	
	@Test
	public void testCalculateEndpointNegativePathInvalidCharacterException() throws Exception {
		String request = "{\"calculation\":"+null+"\"\"}";
		
		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Input is invalid.";
		
	    assertEquals(message, response.getBody());
	}
	
	@Test
	public void testCalculateEndpointNegativePathDivisionByZeroException() throws Exception {
		String request = "{\"calculation\": \"4/0\"}";
		
		ResponseEntity<?> response = calculatorController.calculate(request);
		
		String message = "Division by 0 is not allowed.";
		
	    assertEquals(message, response.getBody());
	}

	@Test
	public void testStoreEndpointHappyPath() throws Exception {
		String request = "{\"storage\": \"4/2\"}";
		ResponseEntity<?> response = calculatorController.store(request);
		assertEquals("{\"result\": \"4/2\"}", response.getBody());
	}
	
	@Test
	public void testStoreEndpointNegativePathStorageException() throws Exception {
		String request = "{\"calculation\": \"4/2a\"}";
		
		ResponseEntity<?> response = calculatorController.store(request);
		
		String message = "Input cannot be saved.";
		
	    assertEquals(message, response.getBody());
	}
	
	@Test
	public void testGetStoreEndpoint() throws Exception {
		String request = "{\"storage\": \"6\"}";
		calculatorController.store(request);
		String response = calculatorController.getStore();
		assertEquals("{\"result\": \"6\"}", response);
	}
}
