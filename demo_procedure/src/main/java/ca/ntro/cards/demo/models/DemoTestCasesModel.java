package ca.ntro.cards.demo.models;

import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.ExecutionEngine;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.core.initialization.Ntro;

public class   DemoTestCasesModel<STUDENT_MODEL extends TriNaif> 

       extends TestCasesModel<TriNaif, STUDENT_MODEL, DemoTestCase>{

	@Override
	public void describeTestCasesToGenerate() {

		TestCaseDescriptor descriptor = TestCaseDescriptor.create()
				                                          .category("examples")
				                                          .testCaseId("ex01");
		
		addTestCase(descriptor);
		
		
		for(int i = 0; i < 10; i++) {
			
			int size = 10 + Ntro.random().nextInt(100);
			

			descriptor = TestCaseDescriptor.create()
					                       .random(size);
			
			addTestCase(descriptor);
		}
	}

}
