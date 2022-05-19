package ca.ntro.cards.models.values;

import ca.ntro.app.models.Value;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.core.identifyers.Identifiable;

public class TestCase<CARDS_MODEL extends CardsModel> implements Value, Identifiable {
	
	private String category;
	private long testCaseId;
	private long size;

	private CARDS_MODEL startingState;
	private boolean passed;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public CARDS_MODEL getStartingState() {
		return startingState;
	}

	public void setStartingState(CARDS_MODEL startingState) {
		this.startingState = startingState;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	@Override
	public String id() {
		return category + "_" + String.valueOf(size) + "_" + testCaseId;
	}
}
