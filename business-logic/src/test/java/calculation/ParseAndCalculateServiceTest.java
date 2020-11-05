package calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParseAndCalculateServiceTest {
	private final ParseAndCalculateService parseAndCalculateService = new ParseAndCalculateService();

	@Test
	public void testParseAndCalculate() throws Exception {
		String input = "{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}";
		assertEquals("2", parseAndCalculateService.parseAndCalculate(input));
		
		input = "{\r\n" + "\"calculation\": \"40+   32.5\"\r\n" + "}";
		assertEquals("72.5", parseAndCalculateService.parseAndCalculate(input));
		
		final String wrongInput = "{\r\n" + "\"calculation\": \"4/2****\"\r\n" + "}";
		assertThrows(java.lang.Exception.class, () -> {
			parseAndCalculateService.parseAndCalculate(wrongInput);
		});

	}
}