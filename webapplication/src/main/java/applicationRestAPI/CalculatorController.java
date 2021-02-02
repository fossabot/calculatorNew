package applicationRestAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import calculation.ParseAndCalculateService;
import exceptions.CalculationException;
import exceptions.DivisionByZeroException;
import exceptions.StorageException;
import storage.StorageService;

@RestController
public class CalculatorController {
	//This is a test
	private ParseAndCalculateService parserAndCalculationService = new ParseAndCalculateService();
	private StorageService storageService = new StorageService();

	@CrossOrigin(origins = "http://localhost:5000")
	@PostMapping(value = "/calculation", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> calculate(@RequestBody String request) {
		try {
			String result = parserAndCalculationService.parseAndCalculate(request);
			return new ResponseEntity<>("{\"result\": \""+result+"\"}", HttpStatus.OK);
		}
		catch(CalculationException e) {
			return new ResponseEntity<>("Input cannot be calculated.", HttpStatus.NOT_ACCEPTABLE);
		}
		catch(DivisionByZeroException e) {
			return new ResponseEntity<>("Division by 0 is not allowed.", HttpStatus.FORBIDDEN);
		}
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@PostMapping(value = "/storage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> store(@RequestBody String request) {
		try {
			String storedValue = storageService.store(request);
			return new ResponseEntity<>("{\"result\": \""+storedValue+"\"}", HttpStatus.OK);
		}
		catch(StorageException e) {
			return new ResponseEntity<>("Input cannot be saved.", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/storage", produces = "application/json")
	public String getStore() {
		return "{\"result\": \""+storageService.getStorage()+"\"}";
	}
	
	void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	void setParserAndCalculationService(ParseAndCalculateService parserAndCalculationService) {
		this.parserAndCalculationService = parserAndCalculationService;
	}
}