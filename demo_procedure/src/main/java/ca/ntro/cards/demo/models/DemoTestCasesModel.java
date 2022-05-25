package ca.ntro.cards.demo.models;

import ca.ntro.cards.common.test_cases.TestCasesDatabase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.core.initialization.Ntro;

public class   DemoTestCasesModel<STUDENT_MODEL extends TriNaif> 

       extends TestCasesDatabase<TriNaif, STUDENT_MODEL, DemoTestCase>{

	@Override
	public void describeTestCasesToGenerate() {

		TestCaseDescriptor descriptor = TestCaseDescriptor.create()
				                                          .category("examples")
				                                          .testCaseId("ex01");
		
		addTestCase(descriptor);
		
		
		for(int i = 0; i < 2; i++) {
			
			int size = 10 + Ntro.random().nextInt(2);
			

			descriptor = TestCaseDescriptor.create()
					                       .random(size);
			
			addTestCase(descriptor);
		}
	}

}
