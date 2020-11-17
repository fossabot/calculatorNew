package storage;

public class StoreException extends Exception {
	
	StoreException() {
		super("Fehler. Eingabe kann nicht gespeichert werden.");
	}
}