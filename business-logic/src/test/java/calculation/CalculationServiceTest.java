package calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import parser.CalculationObject;

public class CalculationServiceTest {
	private final CalculationService calculationService = new CalculationService();
	
	@Test
	public void testAddition() throws Exception {
        assertEquals("6", calculationService.getResult(new CalculationObject("4", "+", "2")));
        assertEquals("0", calculationService.getResult(new CalculationObject("-2", "+", "2")));
        assertEquals("12.5", calculationService.getResult(new CalculationObject("10.5", "+", "2")));
        assertEquals("6.7", calculationService.getResult(new CalculationObject("4.5", "+", "2.2")));
	}
	
	@Test
	public void testSubtraction() throws Exception {
        assertEquals("2", calculationService.getResult(new CalculationObject("4", "-", "2")));
        assertEquals("-6", calculationService.getResult(new CalculationObject("-4", "-", "2")));
        assertEquals("2.5", calculationService.getResult(new CalculationObject("4.5", "-", "2")));
        assertEquals("19.5", calculationService.getResult(new CalculationObject("40", "-", "20.5")));
	}
	
	@Test
	public void testMultiplication() throws Exception {
        assertEquals("8", calculationService.getResult(new CalculationObject("4", "*", "2")));
        assertEquals("9", calculationService.getResult(new CalculationObject("4.5", "*", "2")));
        assertEquals("-12", calculationService.getResult(new CalculationObject("-4", "*", "3")));
        assertEquals("8", calculationService.getResult(new CalculationObject("-4", "*", "-2")));
	}
	
	@Test
	public void testDivision() throws Exception {
        assertEquals("2", calculationService.getResult(new CalculationObject("4", "/", "2")));
        assertEquals("2.2", calculationService.getResult(new CalculationObject("4.4", "/", "2")));
        assertEquals("-2", calculationService.getResult(new CalculationObject("-4", "/", "2")));
        assertEquals("16", calculationService.getResult(new CalculationObject("40", "/", "2.5")));
	}
	
	@Test
	public void testDivisionbyZeroThrowsException() throws Exception {
		assertThrows(java.lang.Exception.class, () -> {
			calculationService.getResult(new CalculationObject("4", "/", "0"));
		});
	}
	
	@Test
	public void testGetResultDefault() throws Exception {
		assertThrows(java.lang.Exception.class, () -> {
			calculationService.getResult(new CalculationObject("4", "%", "2"));
		});
	}
}