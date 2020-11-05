package applicationRestAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import calculation.CalculationService;
import calculation.ParseAndCalculateService;
import parser.CalculationObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CalculatorControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParseAndCalculateService parseAndCalculationServiceMocked;
	
	CalculationObject calc = new CalculationObject("2", "+", "4");
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCalculate() throws Exception {   
		mockMvc.perform(post("/calculation")
           	.contentType(MediaType.APPLICATION_JSON)
           	.content(mapper.writeValueAsString(calc)));
		
	}

}
