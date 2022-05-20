package ca.ntro.cards.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.models.ExploreCardsModel;

public class TestCasesBySize<CARDS_MODEL extends ExploreCardsModel> implements Value {
	
	private Map<String, TestCase<CARDS_MODEL>> bySize = new HashMap<>();

	public Map<String, TestCase<CARDS_MODEL>> getBySize() {
		return bySize;
	}

	public void setBySize(Map<String, TestCase<CARDS_MODEL>> bySize) {
		this.bySize = bySize;
	}
}
