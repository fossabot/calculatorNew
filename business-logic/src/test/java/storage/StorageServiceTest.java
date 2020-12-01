package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import exceptions.StorageException;

public class StorageServiceTest {

	private final StorageService storageService = new StorageService();
	
	@Test
	public void storeDefault() throws Exception {
		assertEquals("", storageService.getStorage());
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
	public void throwsStorageExceptionAfterInvalidInput() {
		String wrongObject = "{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}";
		
		StorageException e = assertThrows(StorageException.class, () -> {
			storageService.store(wrongObject);
		});
		
		assertEquals("The input could not be stored.", e.getMessage());
	}
	
	@Test
	public void resetToDefaultAfterInvalidInput() throws StorageException {
		storageService.store("{\r\n" + "\"storage\": \"8\"\r\n" + "}");
		
		assertThrows(StorageException.class, () -> {
			storageService.store("{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}");
		});
		
		assertEquals("", storageService.getStorage());
	}
}