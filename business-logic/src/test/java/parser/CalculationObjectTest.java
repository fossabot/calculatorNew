package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculationObjectTest {
	CalculationObject firstCalculation = new CalculationObject("2", "+", "1");
	CalculationObject secondCalculation = new CalculationObject();
	
	@Test
	public void testGetterSetterCalculationObject() {
		assertEquals("2", firstCalculation.getOperand1());
		assertEquals("+", firstCalculation.getOperator());
		assertEquals("1", firstCalculation.getOperand2());
		
		secondCalculation.setOperand1("3");
		secondCalculation.setOperator("/");
		secondCalculation.setOperand2("6");
		
		assertEquals("3", secondCalculation.getOperand1());
		assertEquals("/", secondCalculation.getOperator());
		assertEquals("6", secondCalculation.getOperand2());
	}
}
