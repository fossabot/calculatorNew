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
	private StorageService storageService = new StorageService();

	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/calculation", consumes = "application/json", produces = "application/json")
	public String calculate(@RequestBody String request) {
		String result = calculationService.calculate(request);
		return "{\"result\": \""+result+"\"}";
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/storage", consumes = "application/json")
	public boolean store(@RequestBody String request) {
		boolean worked = storageService.store(request);
		return worked;
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/storage", produces = "application/json")
	public String getStore() {
		return "{\"result\": \""+storageService.getStorage()+"\"}";
	} 
}