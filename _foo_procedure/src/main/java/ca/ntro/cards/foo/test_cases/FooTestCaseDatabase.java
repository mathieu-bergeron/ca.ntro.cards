package ca.ntro.cards.foo.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.foo.models.values.FooTestCase;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   FooTestCaseDatabase<STUDENT_MODEL extends FooCardsModel> 

       extends ProcedureTestCaseDatabase<FooCardsModel, 
                                         STUDENT_MODEL, 
                                         FooTestCase, 
                                         FooExecutionTrace,
                                         FooProcedureDashboardModel> {

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
