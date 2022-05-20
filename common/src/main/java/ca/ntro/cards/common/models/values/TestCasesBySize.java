package ca.ntro.cards.common.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCardsModel;

public class TestCasesBySize<CARDS_MODEL extends CommonCardsModel> implements Value {
	
	private Map<String, CommonTestCase<CARDS_MODEL>> bySize = new HashMap<>();

	public Map<String, CommonTestCase<CARDS_MODEL>> getBySize() {
		return bySize;
	}

	public void setBySize(Map<String, CommonTestCase<CARDS_MODEL>> bySize) {
		this.bySize = bySize;
	}
}
