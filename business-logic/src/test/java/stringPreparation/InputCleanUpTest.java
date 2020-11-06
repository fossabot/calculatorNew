package stringPreparation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InputCleanUpTest {
	private final InputCleanUp inputCleanUp = new InputCleanUp();
	
	@Test
	public void testInputCleanUp() {
		assertEquals("1+234", inputCleanUp.deleteBlanks(" 1 +     23    4"));	
	}
}