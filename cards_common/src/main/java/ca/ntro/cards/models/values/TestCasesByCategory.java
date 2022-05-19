package ca.ntro.cards.models.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.models.CardsModel;

public class TestCasesByCategory<CARDS_MODEL extends CardsModel> implements Value {
	
	private Map<String, TestCasesBySize<CARDS_MODEL>> testCases = new HashMap<>();

}
