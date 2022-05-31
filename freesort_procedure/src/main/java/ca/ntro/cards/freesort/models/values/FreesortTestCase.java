package ca.ntro.cards.freesort.models.values;


import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.test_cases.descriptor.FreesortTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   FreesortTestCase<STUDENT_MODEL extends TriLibre> 

       extends ProcedureTestCase<TriLibre, STUDENT_MODEL, ProcedureExecutionTrace, FreesortProcedureDashboardModel> {

	@Override
	protected CommonTestCaseDescriptor newTestCaseDescriptor() {
		return new FreesortTestCaseDescriptor();
	}

}
