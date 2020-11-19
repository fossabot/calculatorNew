package calculation;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import parser.CalculationObject;

public class AssertJCalculationServiceTest {
	private final CalculationService calculationService = new CalculationService();

	@Test
	public void additionOfTwoPositiveIntegers() throws Exception {
		CalculationObject c = new CalculationObject("40", "+", "6");
		String expected = "46";
		String actual = calculationService.getResult(c);
		
		assertThat(actual).isEqualTo(expected);
		assertThat(actual.equals(expected)).isTrue();
		assertThat(actual)	.isNotNull()
							.contains("6")
							.startsWith("4")
							.endsWith("6");
		assertThat(actual).as("Get Result").isEqualTo(expected);
	}
	
	@Test
	public void divisionByZeroThrowsExeption() {
		try {
			calculationService.getResult(new CalculationObject("4", "/", "0"));
			fail("Exception expected because division by zero is not allowed.");
		}
		catch(Exception e) {
			assertThat(e).hasMessage("Fehler: Division durch 0 nicht möglich.");
		}
	}
}