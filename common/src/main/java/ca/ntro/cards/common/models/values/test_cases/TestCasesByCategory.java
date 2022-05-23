package ca.ntro.cards.common.models.values.test_cases;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

public class TestCasesByCategory<STUDENT_MODEL extends CommonExecutableModel, 
                                 TEST_CASE     extends TestCase<STUDENT_MODEL>> 


       implements Value {
	
	private Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> byCategory = new HashMap<>();

	public Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> getByCategory() {
		return byCategory;
	}

	public void setByCategory(Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> byCategory) {
		this.byCategory = byCategory;
	}

	public void addTestCase(TEST_CASE testCase) {
		TestCasesBySize<STUDENT_MODEL, TEST_CASE> bySize = byCategory.get(testCase.getCategory());

		if(bySize == null) {
			bySize = new TestCasesBySize<>();
			byCategory.put(testCase.getCategory(), bySize);
		}
		
		bySize.addTestCase(testCase);
	}

}
