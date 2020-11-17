package stringPreparation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InputCleanUpTest {
	
	private final InputCleanUp inputCleanUp = new InputCleanUp();
	
	@Test
	public void inputCleanUpStringWithBlanks() {
		assertEquals("1+234", inputCleanUp.deleteBlanks(" 1 +     23    4"));	
	}

	@Test
	public void inputCleanUpOnlyBlanks() {
		assertEquals("", inputCleanUp.deleteBlanks("            "));	
	}

	@Test
	public void inputCleanUpEmptyString() {
		assertEquals("", inputCleanUp.deleteBlanks(""));
	}
}