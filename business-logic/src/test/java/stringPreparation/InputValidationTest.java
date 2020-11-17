package stringPreparation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class InputValidationTest {
	
	private final InputValidation inputValidation = new InputValidation();
	
	@Test
	public void checkValidityOfNumbers() throws Exception {
		String validInput = "0123456789";
		
		assertEquals(validInput, inputValidation.checkInputValidity(validInput));
	}
	
	@Test
	public void checkValidityOfSigns() throws Exception {
		String validInput = ".+-/*";
		
		assertEquals(validInput, inputValidation.checkInputValidity(validInput));
	}
	
	@Test
	public void checkValidityOfBlanks() throws Exception {
		String validInput = "   ";
		
		assertEquals(validInput, inputValidation.checkInputValidity(validInput));
	}
	
	@Test
	public void checkValidityOfEmptyString() throws Exception {
		String validInput = "";
		
		assertEquals(validInput, inputValidation.checkInputValidity(validInput));
	}
	
	@Test
	public void checkValidityOfCombiniedString() throws Exception {
		String validInput = "12+12.2-3/7 *0";
		
		assertEquals(validInput, inputValidation.checkInputValidity(validInput));
	}
	
	@Test
	public void checkNullStringThrowsException() {
		String invalidInput = null;
		
		InvalidCharacterException e = assertThrows(InvalidCharacterException.class, () -> {
			inputValidation.checkInputValidity(invalidInput);
		});
		assertEquals("Die Eingabe ist nicht valide.", e.getMessage());
	}

	@Test
	public void checkInvalidCharacterThrowsException() {
		String invalidInput = "2+3%6";
		
		InvalidCharacterException e = assertThrows(InvalidCharacterException.class, () -> {
			inputValidation.checkInputValidity(invalidInput);
		});
		assertEquals("Die Eingabe ist nicht valide.", e.getMessage());
	}
}