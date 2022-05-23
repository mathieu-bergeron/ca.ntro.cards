package ca.ntro.cards.common.models.values.test_cases;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.values.execution_trace.ExecutionTrace;
import ca.ntro.core.identifyers.Identifiable;

public class TestCase<EXECUTABLE_MODEL extends CommonExecutableModel> implements Value, Identifiable {
	
	private String category;
	private String testCaseId;
	private long size;

	private ExecutionTrace<EXECUTABLE_MODEL> trace;
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

	public ExecutionTrace<EXECUTABLE_MODEL> getTrace() {
		return trace;
	}

	public void setTrace(ExecutionTrace<EXECUTABLE_MODEL> trace) {
		this.trace = trace;
	}

	@Override
	public String id() {
		return category + "_" + String.valueOf(size) + "_" + testCaseId;
	}
}
