package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class TestCaseById<STUDENT_MODEL extends CommonExecutableModel, 
                          TEST_CASE     extends AbstractTestCaseDescriptor> 

       implements Value, Serializable {
	
	private Map<String, TEST_CASE> byId = new HashMap<>();

	public Map<String, TEST_CASE> getById() {
		return byId;
	}

	public void setById(Map<String, TEST_CASE> byId) {
		this.byId = byId;
	}

	public void addTestCase(TEST_CASE testCase) {

		byId.put(testCase.testCaseId(), testCase);

	}

	public TEST_CASE addOrUpdateTestCase(TEST_CASE testCase) {
		
		TEST_CASE storedTestCase = byId.get(testCase.testCaseId());
		
		if(storedTestCase != null) {

			// FIXME
			//storedTestCase.copyTracesFrom(testCase);
			addTestCase(testCase);

		}else {

			storedTestCase = testCase;
			addTestCase(testCase);

		}
		
		return storedTestCase;
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

	public TEST_CASE testCaseById(String testCaseId) {
		return byId.get(testCaseId);
	}
	
	public Stream<TEST_CASE> inOrder(){
		return new StreamNtro<>() {

			@Override
			public void forEach_(Visitor<TEST_CASE> visitor) throws Throwable {
				List<String> ids = new ArrayList<>(byId.keySet());

				
				ids.sort((i1,i2) -> {
					
					int result = i1.compareTo(i2);
					
					try {
						
						Integer i1Int = Integer.parseInt(i1);
						Integer i2Int = Integer.parseInt(i2);
						
						result = i1Int.compareTo(i2Int);

					}catch(NumberFormatException e) {}
					
					return result;
				});

				for(String id : ids) {
					visitor.visit(byId.get(id));
				}
			}
		};
	}

	public int size() {
		return byId.size();
	}

}
