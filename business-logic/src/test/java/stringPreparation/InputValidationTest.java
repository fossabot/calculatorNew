package stringPreparation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class InputValidationTest {
	private final InputValidation inputValidation = new InputValidation();
	
	@Test
	public void testInputValidation() throws Exception {
		String input = "2+3+6*0-900012.2";
		assertEquals(input, inputValidation.validateInput(input));
	}
	
	@Test
	public void testInputValidationException() throws Exception {
		String input = "2+3%6";
		assertThrows(java.lang.Exception.class, () -> {
			inputValidation.validateInput(input);
		});
	}
}