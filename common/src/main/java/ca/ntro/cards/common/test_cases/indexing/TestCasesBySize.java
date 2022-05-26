package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;

public class     TestCasesBySize<STUDENT_MODEL extends CommonExecutableModel, 
                                 TEST_CASE     extends CommonTestCase> 

       implements Value, Serializable {
	
	
	private Map<String, TestCaseById<STUDENT_MODEL, TEST_CASE>> bySize = new HashMap<>();

	public Map<String, TestCaseById<STUDENT_MODEL, TEST_CASE>> getBySize() {
		return bySize;
	}

	public void setBySize(Map<String, TestCaseById<STUDENT_MODEL, TEST_CASE>> bySize) {
		this.bySize = bySize;
	}

	public void addTestCase(TEST_CASE testCase) {
		String sizeKey = String.valueOf(testCase.getSize());
		
		TestCaseById<STUDENT_MODEL, TEST_CASE> byId = bySize.get(sizeKey);
		
		if(byId == null) {
			byId = new TestCaseById<>();
			bySize.put(sizeKey, byId);
		}

		byId.addTestCase(testCase);
	}
}
