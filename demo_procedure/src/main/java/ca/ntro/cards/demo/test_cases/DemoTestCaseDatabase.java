package ca.ntro.cards.demo.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   DemoTestCaseDatabase<STUDENT_MODEL extends DemoCardsModel> 

       extends ProcedureTestCaseDatabase<DemoCardsModel, 
                                         STUDENT_MODEL, 
                                         DemoTestCase, 
                                         ProcedureExecutionTrace,
                                         DemoProcedureDashboardModel> {

	@Override
	public void describeTestCasesToGenerate() {

		AbstractTestCaseDescriptor descriptor = AbstractTestCaseDescriptor.create()
				                                          .category("exemples")
				                                          .testCaseId("ex01");
		
		addTestCase(descriptor);
		
		
		for(int i = 1; i <= 2; i++) {
			
			int size = i*50;

			descriptor = AbstractTestCaseDescriptor.create()
												   .random(size);

			addTestCase(descriptor);
		}
	}

}
