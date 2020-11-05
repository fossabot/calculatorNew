package applicationRestAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorControllerTest {

	private CalculatorController calculatorController = new CalculatorController();

	@Test
	public void testCalculateEndpoint() throws Exception {
		String request = "{\"calculation\": \"4/2\"}";
		String response = calculatorController.calculate(request);
		assertEquals("{\"result\": \"2\"}", response);
	}

	@Test
	public void testStoreEndpoint() throws Exception {
		String request = "{\"storage\": \"4/2\"}";
		String response = calculatorController.store(request);
		assertEquals("{\"result\": \"4/2\"}", response);
	}
	
	@Test
	public void testGetStorageEndpoint() throws Exception {
		String request = "{\"storage\": \"6\"}";
		calculatorController.store(request);
		String response = calculatorController.getStore();
		assertEquals("{\"result\": \"6\"}", response);
	}
}