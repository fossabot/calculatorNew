package stringPreparation;

import org.junit.jupiter.api.Test;

import exceptions.InvalidCharacterException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Nested;

public class StringPreparationTest {
	
	private final StringPreparation stringPreparation = new StringPreparation();
	
	@Nested
	class InputCleanUp {
		@Test
		public void inputCleanUpStringWithBlanks() {
			assertEquals("1+234", stringPreparation.deleteBlanks(" 1 +     23    4"));	
		}
		
		@Test
		public void inputCleanUpStringOnlyBlanks() {
			assertEquals("", stringPreparation.deleteBlanks("            "));	
		}

		@Test
		public void inputCleanUpEmptyString() {
			assertEquals("", stringPreparation.deleteBlanks(""));
		}
	}
	
	@Nested
	class checkCharValidity {
		@Test
		public void checkValidityOfNumbers() throws InvalidCharacterException {
			String input = "0123456789";
			
			assertEquals(input, stringPreparation.checkInputValidity(input));
		}
		
		@Test
		public void checkValidityOfSigns() throws InvalidCharacterException {
			String input = ".+-/*";
			
			assertEquals(input, stringPreparation.checkInputValidity(input));
		}
		
		@Test
		public void checkValidityOfBlanks() throws InvalidCharacterException {
			String input = "   ";
			String expected = "";
			
			assertEquals(expected, stringPreparation.checkInputValidity(input));
		}
		
		@Test
		public void checkValidityOfEmptyString() throws InvalidCharacterException {
			String input = "";
			
			assertEquals(input, stringPreparation.checkInputValidity(input));
		}
		
		@Test
		public void checkValidityOfCombiniedString() throws InvalidCharacterException {
			String input = "12+12.2-3/7 *0";
			String expected = "12+12.2-3/7*0";
			
			assertEquals(expected, stringPreparation.checkInputValidity(input));
		}
		
		@Test
		public void checkNullStringThrowsInvalidCharacterException() {
			String invalidInput = null;
			
			InvalidCharacterException e = assertThrows(InvalidCharacterException.class, () -> {
				stringPreparation.checkInputValidity(invalidInput);
			});
			assertEquals("The input is not valid.", e.getMessage());
		}

		@Test
		public void checkInvalidCharacterThrowsInvalidCharacterException() {
			String invalidInput = "2+3%6";
			
			InvalidCharacterException e = assertThrows(InvalidCharacterException.class, () -> {
				stringPreparation.checkInputValidity(invalidInput);
			});
			assertEquals("The input is not valid.", e.getMessage());
		}
	}
}