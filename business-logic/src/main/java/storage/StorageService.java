package storage;

import org.json.JSONException;
import org.json.JSONObject;

public class StorageService {
	public String answer;
	public String storage = "";
	
	public String store(String store) throws Exception {
		
		
		try {
			JSONObject obj =  new JSONObject(store);
			this.answer = obj.getString("storage");
			this.storage = this.answer;
		}
		catch(JSONException jsonException) {
			this.storage = "";
			//TODO HTTP Response
		}
		return this.answer;
	}

	//TODO Default Storage should not be null
	//TODO If the default storage was changed, change the test case as well
	public String getStorage() {
		return this.storage;
	}
}