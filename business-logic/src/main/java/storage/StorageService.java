package storage;

import org.json.JSONException;
import org.json.JSONObject;
import exceptions.StorageException;

public class StorageService {
	public String toStore;
	public String storage = "";
	
	public String store(String inputInJSONFormat) throws StorageException {
		
		try {
			JSONObject obj =  new JSONObject(inputInJSONFormat);
			this.toStore = obj.getString("storage");
			this.storage = this.toStore;
		}
		catch(JSONException jsonException) {
			this.storage = "";
			throw new StorageException();
		}
		return this.toStore;
	}

	public String getStorage() {
		return this.storage;
	}
}