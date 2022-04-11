package ca.ntro.core.services;

import ca.ntro.core.json.JsonObject;

public interface JsonService {

	JsonObject newJsonObject();
	JsonObject fromJsonString(String jsonString);

}
