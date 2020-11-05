package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StorageServiceTest {
	private final StorageService storageService = new StorageService();
	String object = "{\r\n" + "\"storage\": \"6\"\r\n" + "}";
	
	@Test
	public void testStore() throws Exception {
		assertEquals("6", storageService.store(object));
	}
	
	@Test
	public void testStore_Exception() throws Exception {
		String wrongObject = "{\r\n" + "\"calculation\": \"4/2\"\r\n" + "}";
		
		Exception e = assertThrows(java.lang.Exception.class, () -> {
			storageService.store(wrongObject);
		});
		assertEquals("Fehler. Eingabe kann nicht gespeichert werden", e.getMessage());
	}
	
	@Test
	public void testGetStorage() throws Exception {
		storageService.store(object);
		assertEquals("6", storageService.getStorage());
	}
}
