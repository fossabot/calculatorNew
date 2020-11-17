package calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import parser.CalculationObject;

public class CalculationServiceTest {
	private final CalculationService calculationService = new CalculationService();

	@Nested
	class Addition {
		static final String PLUS = "+";
		@DisplayName("Tests for addition")
	
		@Test
		public void additionOfTwoPositiveIntegers() throws Exception {
			assertEquals("6", calculationService.getResult(new CalculationObject("4", PLUS, "2")));
		}
		
		@Test
		public void additionOfTwoIntegersFirstNegative() throws Exception {
			assertEquals("-2", calculationService.getResult(new CalculationObject("-4", PLUS, "2")));
		}
		
		@Test
		public void additionOfTwoDecimalNumbers() throws Exception {
			assertEquals("7", calculationService.getResult(new CalculationObject("4.2", PLUS, "2.8")));
		}
		
		@Test
		public void additionOfTwoDecimalNumbersFirstNegative() throws Exception {
			assertEquals("-2", calculationService.getResult(new CalculationObject("-4.2", PLUS, "2.2")));
		}
	}
	
	@Nested
	class Subtraction {
		static final String MINUS = "-";
		@DisplayName("Tests for subtraction")
	
		@Test
		public void subtractionOfTwoPositiveIntegers() throws Exception {
			assertEquals("2", calculationService.getResult(new CalculationObject("4", MINUS, "2")));
		}
		
		@Test
		public void subtractionOfTwoIntegersFirstNegative() throws Exception {
			assertEquals("-6", calculationService.getResult(new CalculationObject("-4", MINUS, "2")));
		}
		
		@Test
		public void subtractionOfTwoDecimalNumbers() throws Exception {
			assertEquals("2", calculationService.getResult(new CalculationObject("4.2", MINUS, "2.2")));
		}
		
		@Test
		public void subtractionOfTwoDecimalNumbersFirstNegative() throws Exception {
			assertEquals("-6.4", calculationService.getResult(new CalculationObject("-4.2", MINUS, "2.2")));
		}
	}
	
	@Nested
	class Multiplication {
		static final String TIMES = "*";
		@DisplayName("Tests for multiplication")
		
		@Test
		public void multiplicationOfTwoPositiveIntegers() throws Exception {
			assertEquals("8", calculationService.getResult(new CalculationObject("4", TIMES, "2")));
		}

		@Test
		public void multiplicationOfTwoIntegersFirstNegative() throws Exception {
			assertEquals("-8", calculationService.getResult(new CalculationObject("-4", TIMES, "2")));
		}
		
		@Test
		public void multiplicationOfTwoDecimalNumbers() throws Exception {
			assertEquals("9.24", calculationService.getResult(new CalculationObject("4.2", TIMES, "2.2")));
		}
		
		@Test
		public void multiplicationOfTwoDecimalNumbersFirstNegative() throws Exception {
			assertEquals("-9.24", calculationService.getResult(new CalculationObject("-4.2", TIMES, "2.2")));
		}
	}
	
	@Nested
	class Division {
		static final String DIVIDE = "/";

		@DisplayName("Tests for division")
		@Test
		public void divisionOfTwoPositiveIntegers() throws Exception {
			assertEquals("2", calculationService.getResult(new CalculationObject("4", DIVIDE, "2")));
		}
		
		@Test
		public void divisionOfTwoIntegersFirstNegative() throws Exception {
			assertEquals("-2", calculationService.getResult(new CalculationObject("-4", DIVIDE, "2")));
		}
		
		@Test
		public void divisionOfTwoDecimalNumbers() throws Exception {
			assertEquals("1.909091", calculationService.getResult(new CalculationObject("4.2", DIVIDE, "2.2")));
		}

		@Test
		public void divisionOfTwoDecimalNumbersFirstNegative() throws Exception {
			assertEquals("-1.909091", calculationService.getResult(new CalculationObject("-4.2", DIVIDE, "2.2")));
		}
		
		@Test
		public void divisionbyZero() {
			Exception e = assertThrows(java.lang.Exception.class, () -> {
				calculationService.getResult(new CalculationObject("4", "/", "0"));
			});
			assertEquals("Fehler: Division durch 0 nicht möglich.", e.getMessage());
		}
	}

	@Test
	public void getResultDefault() {
		CalculationException e = assertThrows(CalculationException.class, () -> {
			calculationService.getResult(new CalculationObject("4", "%", "2"));
		});
		assertEquals("Fehler beim Berechnen der Eingabe.", e.getMessage());
	}
	
	@Test
	public void outputToStringInteger() {
		BigDecimal result = new BigDecimal("30");
		
		assertEquals("30", calculationService.outputToString(result));
	}
	
	@Test
	public void outputToStringDecimalNumber() {
		BigDecimal result = new BigDecimal("30.3");
		
		assertEquals("30.3", calculationService.outputToString(result));
	}
}