package ca.ntro.cards.common.test_cases.indexing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class TestCasesByCategory<STUDENT_MODEL extends CommonExecutableModel, 
                                 TEST_CASE     extends TestCaseDescriptor> 


       implements Value, Serializable {
	
	private Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> byCategory = new HashMap<>();

	public Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> getByCategory() {
		return byCategory;
	}

	public void setByCategory(Map<String, TestCasesBySize<STUDENT_MODEL, TEST_CASE>> byCategory) {
		this.byCategory = byCategory;
	}

	public void addTestCase(TEST_CASE testCase) {
		TestCasesBySize<STUDENT_MODEL, TEST_CASE> bySize = byCategory.get(testCase.category());

		if(bySize == null) {
			bySize = new TestCasesBySize<>();
			byCategory.put(testCase.category(), bySize);
		}
		
		bySize.addTestCase(testCase);
	}
	
	
	public Stream<TEST_CASE> inOrder(){
		return new StreamNtro<>(){
			@Override
			public void forEach_(Visitor<TEST_CASE> visitor) throws Throwable {
				List<String> categories = new ArrayList<>(byCategory.keySet());

				categories.sort((c1,c2) -> c1.compareTo(c2));
				
				for(String category : categories) {
					
					byCategory.get(category).inOrder().forEach(testCase -> {

						visitor.visit(testCase);
						
					});
				}
			}
		};
	}
	
	

}
