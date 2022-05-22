package ca.ntro.cards.common.models.values;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.core.identifyers.Identifiable;

public class TestCase<CARDS_MODEL extends CommonCanvasModel> implements Value, Identifiable {
	
	private String category;
	private String testCaseId;
	private long size;

	private CARDS_MODEL model;
	private boolean passed;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public CARDS_MODEL getModel() {
		return model;
	}

	public void setModel(CARDS_MODEL model) {
		this.model = model;
	}

	@Override
	public String id() {
		return category + "_" + String.valueOf(size) + "_" + testCaseId;
	}
}
