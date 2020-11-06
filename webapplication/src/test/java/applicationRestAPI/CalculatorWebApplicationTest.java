package applicationRestAPI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorWebApplicationTest {

	@Test
	public void contextLoads() {
		CalculatorWebApplication app = Mockito.mock(CalculatorWebApplication.class);
		
	}

}