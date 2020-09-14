package restService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
	
	private CalculationService calculationService = new CalculationService();

	//Calculation of given input
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
	public String calculatse(@RequestBody String request) {
		String result = calculationService.seperate(request);
		return "{\"result\": \""+result+"\"}";
	}
	
	//Storage of given input
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/store", consumes = "application/json", produces = "application/json")
	public void store(@RequestBody String request) {
		calculationService.store(request);	
	}
	
	//Getting stored value
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/store", produces = "application/json")
	public String getStore() {
		return "{\"result\": \""+calculationService.getStorage()+"\"}";
	} 
}