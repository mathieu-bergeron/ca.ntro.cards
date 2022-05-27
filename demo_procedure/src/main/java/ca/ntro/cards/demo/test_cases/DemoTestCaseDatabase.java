package ca.ntro.cards.demo.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.core.initialization.Ntro;

public class   DemoTestCaseDatabase<STUDENT_MODEL extends DemoCardsModel> 

       extends ProcedureTestCaseDatabase<DemoCardsModel, 
                                         STUDENT_MODEL, 
                                         DemoTestCase, 
                                         DemoProcedureDashboardModel> {

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
