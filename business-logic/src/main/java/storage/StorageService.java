package storage;

import org.json.JSONObject;

public class StorageService {
	public String answer;
	public String storage;
	
	public String store(String store) throws Exception {
		JSONObject obj =  new JSONObject(store);
		try {
			this.answer = obj.getString("storage");
			this.storage = this.answer;
		}
		catch (Exception e) {
			this.storage = "";
			throw new Exception("Fehler. Eingabe kann nicht gespeichert werden");
		}
		return this.answer;
	}

	public String getStorage() {
		return this.storage;
	}
}