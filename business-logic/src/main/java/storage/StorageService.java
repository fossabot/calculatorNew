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
			throw new StoreException();
		}
		return this.answer;
	}

	//TODO Default Storage should not be null
	//TODO If the default storage was changed, change the test case as well
	public String getStorage() {
		return this.storage;
	}
}