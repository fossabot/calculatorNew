package applicationRestAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import calculation.ParseAndCalculateService;
import storage.StorageService;


public class CalculatorControllerTest {

	private CalculatorController calculatorController = new CalculatorController();
	
	@Test
	public void testCalculateHappyPath() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/2\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenReturn("2");
		String result = calculatorController.calculate(request);
		
		assertEquals("{\"result\": \"2\"}", result);
	}

	@Test
	public void testCalculateNegativePath() throws Exception {
		ParseAndCalculateService parseAndCalculateService = Mockito.mock(ParseAndCalculateService.class);
		String request = "{\"calculation\": \"4/2a\"}";
		calculatorController.setParserAndCalculationService(parseAndCalculateService);
	
		Mockito.when(parseAndCalculateService.parseAndCalculate(request)).thenThrow(Exception.class);
	    assertThrows(Exception.class, () -> {
	    	calculatorController.calculate(request);
	    });
	}
	
	@Test
	public void testStoreHappyPath() throws Exception {		
		StorageService storageService = Mockito.mock(StorageService.class);
		String request = "{\"storage\": \"4\"}";
		calculatorController.setStorageService(storageService);
	
		Mockito.when(storageService.store(request)).thenReturn("4");
		String result = calculatorController.store(request);
		
		assertEquals("{\"result\": \"4\"}", result);
	}

	@Test
	public void testStoreNegativePath() throws Exception {
		StorageService storageService = Mockito.mock(StorageService.class);
		String request = "{\"calculation\": \"4\"}";
		calculatorController.setStorageService(storageService);
	
		Mockito.when(storageService.store(request)).thenThrow(Exception.class);
	    assertThrows(Exception.class, () -> {
	    	calculatorController.store(request);
	    });
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