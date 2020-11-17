package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StorageServiceTest {

	private final StorageService storageService = new StorageService();
	
	@Test
	public void storeDefault() throws Exception {
		assertEquals(null, storageService.getStorage());
	}
	
	@Test
	public void storeAndReturn() throws Exception {
		String valueToStore = "8";
		String object = "{\r\n" + "\"storage\": \"" + valueToStore + "\"\r\n" + "}";
		
		String result = storageService.store(object);
		
		assertEquals(valueToStore, result);
		assertEquals(valueToStore, storageService.getStorage());
	}
	
	@Test
	public void throwsExceptionAfterInvalidInput() {
		String wrongObject = "{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}";
		
		StoreException e = assertThrows(StoreException.class, () -> {
			storageService.store(wrongObject);
		});
		assertEquals("Fehler. Eingabe kann nicht gespeichert werden.", e.getMessage());
	}
	
	@Test
	public void resetToDefaultAfterInvalidInput() throws Exception {
		storageService.store("{\r\n" + "\"storage\": \"8\"\r\n" + "}");
		
		assertThrows(StoreException.class, () -> {
			storageService.store("{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}");
		});
		
		assertEquals("", storageService.getStorage());
	}
}