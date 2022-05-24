package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class TestCaseById<STUDENT_MODEL extends CommonExecutableModel, 
                          TEST_CASE     extends TestCase> 

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
	
	public Stream<TEST_CASE> testCases(){
		return new StreamNtro<TEST_CASE>() {

			@Override
			public void forEach_(Visitor<TEST_CASE> visitor) throws Throwable {
				
				for(TEST_CASE testCase : byId.values()) {
					
					visitor.visit(testCase);

				}
			}

		};
	}

}
