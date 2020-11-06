package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserServiceTest {
	private final ParserService parserService = new ParserService();
	
	@Test
	public void testParsingInput() throws Exception {
		CalculationObject calculationObjectOne = new CalculationObject("2", "+", "2");
		CalculationObject calculationObjectTwo = new CalculationObject("-2", "+", "2");
		
		assertEquals(calculationObjectOne.getOperand1(), parserService.parseInput("2+2").getOperand1());
		assertEquals(calculationObjectTwo.getOperand1(), parserService.parseInput("-2+2").getOperand1());
	}

	@Test
	public void testParsingInputException() throws Exception {
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("2");
		});
		assertEquals("Keine Rechenoperation", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("abc");
		});
		assertEquals("Parser-Error: Zeichen kann nicht identifiziert werden", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("21+a");
		});
		assertEquals("Parser-Error: Zeichen kann nicht identifiziert werden", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("67.3+2.2.");
		});
		assertEquals("Keine valide Eingabe", e.getMessage());

		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("67.3.+2.");
		});
		assertEquals("Keine valide Eingabe", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("67.3+ +");
		});
		assertEquals("Parser-Error: Zeichen kann nicht identifiziert werden", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("+");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
		
		e = assertThrows(java.lang.Exception.class, () -> {
			 parserService.parseInput("1++");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
	}

	@Test
	public void testCheckIfOperatorException() throws Exception {
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			parserService.checkIfOperator("number");
		});
		assertEquals("Keine valide Rechenoperation", e.getMessage());
	}
}