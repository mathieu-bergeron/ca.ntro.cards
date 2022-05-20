package ca.ntro.cards.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.models.ExploreCardsModel;

public class TestCasesByCategory<CARDS_MODEL extends ExploreCardsModel> implements Value {
	
	private Map<String, TestCasesBySize<CARDS_MODEL>> byCategory = new HashMap<>();

	public Map<String, TestCasesBySize<CARDS_MODEL>> getByCategory() {
		return byCategory;
	}

	public void setByCategory(Map<String, TestCasesBySize<CARDS_MODEL>> byCategory) {
		this.byCategory = byCategory;
	}

}
