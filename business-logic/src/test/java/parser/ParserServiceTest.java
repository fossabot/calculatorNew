package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import exceptions.CalculationException;

public class ParserServiceTest {
	
	private final ParserService parserService = new ParserService();
	
	@Nested
	class parseInputGetOperand1 {
		
		@Test
		public void identifyingOperand1PositiveInteger() throws CalculationException {
			String operand1 = "30";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1NegativeInteger() throws CalculationException {
			String operand1 = "-30";
			
			CalculationObject c = parserService.parseInput("-30+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1PositiveDecimalNumber() throws CalculationException {
			String operand1 = "30.3";
			
			CalculationObject c = parserService.parseInput("30.3+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1NegativeDecimalNumber() throws CalculationException {
			String operand1 = "-30.3";
			
			CalculationObject c = parserService.parseInput("-30.3+6");
			assertEquals(operand1, c.getOperand1());
		}
	
		@Test
		public void parsingInvalidInputWithoutOperand1ThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("+3");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputWithWrongOperand1ThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("abc+2");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputOperand1WithTooManyDotsThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("67.3.+2.2");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
	}
	
	@Nested
	class parseInputGetOperator {

		@Test
		public void identifyingOperatorPlus() throws CalculationException {
			String operator = "+";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorMinus() throws CalculationException {
			String operator = "-";
			
			CalculationObject c = parserService.parseInput("30-6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorTimes() throws CalculationException {
			String operator = "*";
			
			CalculationObject c = parserService.parseInput("30*6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorDivide() throws CalculationException {
			String operator = "/";
			
			CalculationObject c = parserService.parseInput("30/6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void parsingInputWithoutOperatorThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("43");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
	}
	
	@Nested
	class parseInputGetOperand2 {

		@Test
		public void identifyingOperand2PositiveInteger() throws CalculationException {
			String operand2 = "6";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operand2, c.getOperand2());
		}
		
		@Test
		public void identifyingOperand2PositiveDecimalNumber() throws CalculationException {
			String operand2 = "6.5";
			
			CalculationObject c = parserService.parseInput("30.3+6.5");
			assertEquals(operand2, c.getOperand2());
		}

		@Test
		public void operand2NegativeIntegerThrowsCalculaionException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("22+-20");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void operand2NegativeDecimalNumberThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("22+-20.2");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputWithoutOperand2ThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("3+");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputWithWrongOperand2ThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("2+abc");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputOperand2WithTooManyDotsThrowsCalculationException() {
			CalculationException e = assertThrows(CalculationException.class, () -> {
				 parserService.parseInput("67.3+2.2.");
			});
			assertEquals("Error while calculating the input.", e.getMessage());
		}
	}
	
	@Test
	public void parsingInputWithoutOperandsThrowsCalculationException() {
		CalculationException e = assertThrows(CalculationException.class, () -> {
			 parserService.parseInput("+");
		});
		assertEquals("Error while calculating the input.", e.getMessage());
	}
	
	@Test
	public void parsingInvalidInputWithTooManyOperantsThrowsCalculationException() {
		CalculationException e = assertThrows(CalculationException.class, () -> {
			 parserService.parseInput("1++");
		});
		assertEquals("Error while calculating the input.", e.getMessage());
	}
}