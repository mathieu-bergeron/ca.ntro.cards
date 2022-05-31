package ca.ntro.cards.freesort.test_cases;

import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.core.initialization.Ntro;

public class   FreesortTestCaseDatabase<STUDENT_MODEL extends TriLibre> 

       extends ProcedureTestCaseDatabase<TriLibre, 
                                         STUDENT_MODEL, 
                                         FreesortTestCase, 
                                         FreesortExecutionTrace,
                                         FreesortProcedureDashboardModel> {

	@Override
	public void describeTestCasesToGenerate() {
		
		AbstractTestCaseDescriptor descriptor = AbstractTestCaseDescriptor.create()
				                                          .category("exemples")
				                                          .testCaseId("ex01");
		
		addTestCase(descriptor);

		descriptor = AbstractTestCaseDescriptor.create()
				                               .category("exemples")
				                               .testCaseId("ex02");
		
		addTestCase(descriptor);

	}

}
