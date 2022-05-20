package ca.ntro.cards.common.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCanvasModel;

public class TestCasesBySize<CARDS_MODEL extends CommonCanvasModel> implements Value {
	
	private Map<String, TestCase<CARDS_MODEL>> bySize = new HashMap<>();

	public Map<String, TestCase<CARDS_MODEL>> getBySize() {
		return bySize;
	}

	public void setBySize(Map<String, TestCase<CARDS_MODEL>> bySize) {
		this.bySize = bySize;
	}
}
