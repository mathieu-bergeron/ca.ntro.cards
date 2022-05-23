package ca.ntro.cards.common.models.values.test_cases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;

public class TestCaseById<STUDENT_MODEL extends CommonExecutableModel, 
                          TEST_CASE     extends TestCase<STUDENT_MODEL>> 

       implements Value, Serializable {
	
	private Map<String, TEST_CASE> byId = new HashMap<>();

	public Map<String, TEST_CASE> getById() {
		return byId;
	}

	public void setById(Map<String, TEST_CASE> byId) {
		this.byId = byId;
	}

	public void addTestCase(TEST_CASE testCase) {

		byId.put(testCase.getTestCaseId(), testCase);

	}

}
