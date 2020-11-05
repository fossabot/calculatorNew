package applicationRestAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import calculation.ParseAndCalculateService;
import storage.StorageService;

@RestController
public class CalculatorController {
	private ParseAndCalculateService parserAndCalculationService = new ParseAndCalculateService();
	private StorageService storageService = new StorageService();

	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/calculation", consumes = "application/json", produces = "application/json")
	public String calculate(@RequestBody String request) throws Exception {
		String result = parserAndCalculationService.parseAndCalculate(request);
		return "{\"result\": \""+result+"\"}";
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/storage", consumes = "application/json", produces = "application/json")
	public String store(@RequestBody String request) throws Exception {
		String storedValue = storageService.store(request);
		return "{\"result\": \""+storedValue+"\"}";
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/storage", produces = "application/json")
	public String getStore() {
		return "{\"result\": \""+storageService.getStorage()+"\"}";
	} 
}