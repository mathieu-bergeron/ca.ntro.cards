package ca.ntro.core.services;

import ca.ntro.core.json.JsonObject;
import ca.ntro.core.json.JsonObjectJdk;

public class JsonServiceJdk implements JsonService {

	@Override
	public JsonObject fromJsonString(String jsonString) {
		return new JsonObjectJdk(jsonString);
	}

	@Override
	public JsonObject newJsonObject() {
		return new JsonObjectJdk();
	}

}
