package ca.ntro.cards.naivesort.models.values;


import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.naivesort.test_cases.descriptor.NaivesortTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   NaivesortTestCase<STUDENT_MODEL extends TriNaif> 

       extends ProcedureTestCase<TriNaif, STUDENT_MODEL, ProcedureExecutionTrace, NaivesortProcedureDashboardModel> {

	@Override
	protected CommonTestCaseDescriptor newTestCaseDescriptor() {
		return new NaivesortTestCaseDescriptor();
	}

}
