package ca.ntro.cards.common.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCanvasModel;

public class TestCaseById<CARDS_MODEL extends CommonCanvasModel> implements Value {
	
	private Map<String, CommonTestCase<CARDS_MODEL>> byId = new HashMap<>();

	public Map<String, CommonTestCase<CARDS_MODEL>> getById() {
		return byId;
	}

	public void setById(Map<String, CommonTestCase<CARDS_MODEL>> byId) {
		this.byId = byId;
	}

}
