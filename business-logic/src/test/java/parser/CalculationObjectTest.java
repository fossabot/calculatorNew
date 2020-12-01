package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculationObjectTest {
	
	CalculationObject calculation = new CalculationObject();
	
	@Test
	public void storeOperand1() {
		String operand1 = "3";
		
		calculation.setOperand1(operand1);
		
		assertEquals(operand1, calculation.getOperand1());
	}
	
	@Test
	public void operand1Default() {
		assertEquals(null, calculation.getOperand1());
	}
	
	@Test
	public void storeOperand2() {
		String operand2 = "3";
		
		calculation.setOperand2(operand2);
		
		assertEquals(operand2, calculation.getOperand2());		
	}
	
	@Test
	public void operand2Default() {
		assertEquals(null, calculation.getOperand2());		
	}
	
	@Test
	public void storeOperator() {
		String operator = "+";
		
		calculation.setOperator(operator);
		
		assertEquals(operator, calculation.getOperator());		
	}
	
	@Test
	public void operatorDefault() {
		assertEquals(null, calculation.getOperator());		
	}
	
	@Test
	public void constructorWithSetValues() {
		String operand1 = "4";
		String operator = "+";
		String operand2 = "3";
		
		CalculationObject c = new CalculationObject(operand1, operator, operand2);
		assertEquals(operand1, c.getOperand1());
		assertEquals(operator, c.getOperator());
		assertEquals(operand2, c.getOperand2());
	}
}