package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParserServiceTest {
	
	private final ParserService parserService = new ParserService();
	
	//TODO Trennen
	
	@Nested
	class parseInputGetOperand1 {
		@DisplayName("Tests for parsing first parameter (operand1)")

		@Test
		public void identifyingOperand1PositiveInteger() throws Exception {
			String operand1 = "30";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1NegativeInteger() throws Exception {
			String operand1 = "-30";
			
			CalculationObject c = parserService.parseInput("-30+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1PositiveDecimalNumber() throws Exception {
			String operand1 = "30.3";
			
			CalculationObject c = parserService.parseInput("30.3+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void identifyingOperand1NegativeDecimalNumber() throws Exception {
			String operand1 = "-30.3";
			
			CalculationObject c = parserService.parseInput("-30.3+6");
			assertEquals(operand1, c.getOperand1());
		}
		
		@Test
		public void parsingInvalidInputWithoutOperand1ThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("+3");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputWithWrongOperand1ThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("abc+2");
			});
			assertEquals("Parser-Error: Zeichen kann nicht identifiziert werden", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputOperand1WithTooManyDotsThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("67.3.+2.2");
			});
			assertEquals("Keine valide Eingabe", e.getMessage());
		}
	}
	
	@Nested
	class parseInputGetOperator {
		@DisplayName("Tests for parsing second parameter (operator)")

		@Test
		public void identifyingOperatorPlus() throws Exception {
			String operator = "+";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorMinus() throws Exception {
			String operator = "-";
			
			CalculationObject c = parserService.parseInput("30-6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorTimes() throws Exception {
			String operator = "*";
			
			CalculationObject c = parserService.parseInput("30*6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void identifyingOperatorDivide() throws Exception {
			String operator = "/";
			
			CalculationObject c = parserService.parseInput("30/6");
			assertEquals(operator, c.getOperator());
		}
		
		@Test
		public void parsingInputWithoutOperatorThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("43");
			});
			assertEquals("Keine Rechenoperation", e.getMessage());
		}
	}
	
	@Nested
	class parseInputGetOperand2 {
		@DisplayName("Tests for parsing third parameter (operand2)")

		@Test
		public void identifyingOperand2PositiveInteger() throws Exception {
			String operand2 = "6";
			
			CalculationObject c = parserService.parseInput("30+6");
			assertEquals(operand2, c.getOperand2());
		}
		
		@Test
		public void identifyingOperand2PositiveDecimalNumber() throws Exception {
			String operand2 = "6.5";
			
			CalculationObject c = parserService.parseInput("30.3+6.5");
			assertEquals(operand2, c.getOperand2());
		}
		
		@Test
		public void operand2NegativeIntegerThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("22+-20");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void operand2NegativeDecimalNumberThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("22+-20.2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}

		@Test
		public void parsingInvalidInputWithoutOperand2ThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("3+");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputWithWrongOperand2ThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2+abc");
			});
			assertEquals("Parser-Error: Zeichen kann nicht identifiziert werden", e.getMessage());
		}
		
		@Test
		public void parsingInvalidInputOperand2WithTooManyDotsThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("67.3+2.2.");
			});
			assertEquals("Keine valide Eingabe", e.getMessage());
		}
	}
	
	@Test
	public void parsingInputWithoutOperandsThrowsException() {
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("+");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
	}
	
	@Test
	public void parsingInvalidInputWithTooManyOperants() {
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("1++");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
	}
	
	@Test
	public void checkingOperatorThrowsExceptionBecauseIsNotOperator() {
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.checkIfOperator("number");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
	}
	
	
	/*
	@Nested
	class parseInputWithAddition {
		static final String PLUS = "+";
		@DisplayName("Tests for input with addition")

		@Test
		public void parsingInputWithPositiveIntegers() throws Exception {
			CalculationObject c = new CalculationObject("2", PLUS, "2");
			CalculationObject cFromInput = parserService.parseInput("2+2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithIntegersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-4", PLUS, "2");
			CalculationObject cFromInput = parserService.parseInput("-4+2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithPositiveDecimalNumbers() throws Exception {
			CalculationObject c = new CalculationObject("2.3", PLUS, "2.2");
			CalculationObject cFromInput = parserService.parseInput("2.3+2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
	
		@Test
		public void parsingInputWithDecimalNumbersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", PLUS, "2.2");
			CalculationObject cFromInput = parserService.parseInput("-2.3+2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingCombiniedInput() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", PLUS, "2");
			CalculationObject cFromInput = parserService.parseInput("-2.3+2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}

		@Test
		public void parsingInputWithIntegersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2+-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeIntegersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2+-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeDecimalNumbersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2.3+-2.2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithDecimalNumbersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2.3+-2.4");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
	}
	
	@Nested
	class parseInputWithSubtraction {
		static final String MINUS = "-";
		@DisplayName("Tests for input with subtraction")

		@Test
		public void parsingInputWithPositiveIntegers() throws Exception {
			CalculationObject c = new CalculationObject("2", MINUS, "2");
			CalculationObject cFromInput = parserService.parseInput("2-2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithIntegersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-4", MINUS, "2");
			CalculationObject cFromInput = parserService.parseInput("-4-2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithPositiveDecimalNumbers() throws Exception {
			CalculationObject c = new CalculationObject("2.3", MINUS, "2.2");
			CalculationObject cFromInput = parserService.parseInput("2.3-2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
	
		@Test
		public void parsingInputWithDecimalNumbersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", MINUS, "2.2");
			CalculationObject cFromInput = parserService.parseInput("-2.3-2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingCombiniedInput() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", MINUS, "2");
			CalculationObject cFromInput = parserService.parseInput("-2.3-2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}

		@Test
		public void parsingInputWithIntegersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2--2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeIntegersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2--2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeDecimalNumbersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2.3--2.2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithDecimalNumbersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2.3--2.4");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
	}
	
	@Nested
	class parseInputWithMultiplication {
		static final String TIMES = "*";
		@DisplayName("Tests for input with multiplication")

		@Test
		public void parsingInputWithPositiveIntegers() throws Exception {
			CalculationObject c = new CalculationObject("2", TIMES, "2");
			CalculationObject cFromInput = parserService.parseInput("2*2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithIntegersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-4", TIMES, "2");
			CalculationObject cFromInput = parserService.parseInput("-4*2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithPositiveDecimalNumbers() throws Exception {
			CalculationObject c = new CalculationObject("2.3", TIMES, "2.2");
			CalculationObject cFromInput = parserService.parseInput("2.3*2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
	
		@Test
		public void parsingInputWithDecimalNumbersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", TIMES, "2.2");
			CalculationObject cFromInput = parserService.parseInput("-2.3*2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingCombiniedInput() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", TIMES, "2");
			CalculationObject cFromInput = parserService.parseInput("-2.3*2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}

		@Test
		public void parsingInputWithIntegersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2*-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeIntegersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2*-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeDecimalNumbersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2.3*-2.2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithDecimalNumbersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2.3*-2.4");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
	}
	
	@Nested
	class parseInputWithDivision {
		static final String DIVIDE = "/";
		@DisplayName("Tests for input with division")

		@Test
		public void parsingInputWithPositiveIntegers() throws Exception {
			CalculationObject c = new CalculationObject("2", DIVIDE, "2");
			CalculationObject cFromInput = parserService.parseInput("2/2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithIntegersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-4", DIVIDE, "2");
			CalculationObject cFromInput = parserService.parseInput("-4/2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingInputWithPositiveDecimalNumbers() throws Exception {
			CalculationObject c = new CalculationObject("2.3", DIVIDE, "2.2");
			CalculationObject cFromInput = parserService.parseInput("2.3/2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
	
		@Test
		public void parsingInputWithDecimalNumbersFirstNegative() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", DIVIDE, "2.2");
			CalculationObject cFromInput = parserService.parseInput("-2.3/2.2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}
		
		@Test
		public void parsingCombiniedInput() throws Exception {
			CalculationObject c = new CalculationObject("-2.3", DIVIDE, "2");
			CalculationObject cFromInput = parserService.parseInput("-2.3/2");
			
			assertEquals(c.getOperand1(), cFromInput.getOperand1());
			assertEquals(c.getOperand2(), cFromInput.getOperand2());
			assertEquals(c.getOperator(), cFromInput.getOperator());
		}

		@Test
		public void parsingInputWithIntegersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2/-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeIntegersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2/-2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithNegativeDecimalNumbersThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("-2.3/-2.2");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
		
		@Test
		public void parsingInputWithDecimalNumbersSecondNegativeThrowsException() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				 parserService.parseInput("2.3/-2.4");
			});
			assertEquals("Keine valide Rechenoperation", e.getMessage());
		}
	}
	*/
}