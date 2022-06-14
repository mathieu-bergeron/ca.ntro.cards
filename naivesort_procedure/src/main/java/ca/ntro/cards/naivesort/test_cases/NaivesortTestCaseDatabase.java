package ca.ntro.cards.naivesort.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   NaivesortTestCaseDatabase<STUDENT_MODEL extends TriNaif> 

       extends ProcedureTestCaseDatabase<TriNaif, 
                                         STUDENT_MODEL, 
                                         NaivesortTestCase, 
                                         NaivesortExecutionTrace,
                                         NaivesortProcedureDashboardModel> {

	@Override
	public void describeTestCasesToGenerate() {

		AbstractTestCaseDescriptor descriptor = AbstractTestCaseDescriptor.create()
				                                          .category("exemples")
				                                          .testCaseId("ex01");
		
		addTestCaseDescriptor(descriptor);
		
		
		for(int i = 1; i <= 10; i++) {
			
			int size = i;

			descriptor = AbstractTestCaseDescriptor.create()
												   .random(size);

			addTestCaseDescriptor(descriptor);
		}
	}

}
