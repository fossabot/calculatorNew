package storage;


public class StorageService {

	public String getStorage() {
		// TODO Auto-generated method stub
		return null;
	}

}
/*
private loeschen calculationService = new loeschen();
public String storage;

public boolean store(String storage) {
	storage = calculationService.objectToString(storage, "store");

	if(storage.length() == 0 || storage.contentEquals("error")) {
		this.storage = "";
	}
	else {
		storage = normalize(storage);
	}
	return true;
}

private String normalize(String storage) {
	if(storage.substring(storage.length() - 1).contentEquals(".")) {
		storage = storage.substring(0, storage.length() - 1);
	}
	this.storage = storage;
	return storage;
}

public String getStorage() {
	return storage;
}
}*/