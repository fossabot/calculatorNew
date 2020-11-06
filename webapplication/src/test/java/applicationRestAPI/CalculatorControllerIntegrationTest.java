package applicationRestAPI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorControllerIntegrationTest {

	private CalculatorController calculatorController = new CalculatorController();

	@Test
	public void testCalculateEndpointHappyPath() throws Exception {
		String request = "{\"calculation\": \"4/2\"}";
		String response = calculatorController.calculate(request);
		assertEquals("{\"result\": \"2\"}", response);
	}
	
	@Test
	public void testCalculateEndpointNegativePath() throws Exception {
		String request = "{\"calculation\": \"4/2a\"}";
		
	    assertThrows(Exception.class, () -> {
	    	calculatorController.calculate(request);
	    });
	}

	@Test
	public void testStoreEndpointHappyPath() throws Exception {
		String request = "{\"storage\": \"4/2\"}";
		String response = calculatorController.store(request);
		assertEquals("{\"result\": \"4/2\"}", response);
	}
	
	@Test
	public void testStoreEndpointNegativePath() throws Exception {
		String request = "{\"calculation\": \"4/2a\"}";
		
	    assertThrows(Exception.class, () -> {
	    	calculatorController.store(request);
	    });
	}
	
	@Test
	public void testGetStorageEndpoint() throws Exception {
		String request = "{\"storage\": \"6\"}";
		calculatorController.store(request);
		String response = calculatorController.getStore();
		assertEquals("{\"result\": \"6\"}", response);
	}
}
