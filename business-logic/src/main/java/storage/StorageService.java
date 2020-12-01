package storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import exceptions.StorageException;

public class StorageService {
	public String toStore;
	public String storage = "";
	private static final Logger LOGGER = LogManager.getLogger();
	
	public String store(String inputInJSONFormat) throws StorageException {
		
		try {
			JSONObject obj =  new JSONObject(inputInJSONFormat);
			this.toStore = obj.getString("storage");
			this.storage = this.toStore;
		}
		catch(JSONException jsonException) {
			this.storage = "";
			jsonException.printStackTrace();
			LOGGER.error(jsonException);
			throw new StorageException();
		}
		return this.toStore;
	}

	public String getStorage() {
		return this.storage;
	}
}