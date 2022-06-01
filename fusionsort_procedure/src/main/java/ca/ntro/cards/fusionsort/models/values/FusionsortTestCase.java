package ca.ntro.cards.fusionsort.models.values;


import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.test_cases.descriptor.FusionsortTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   FusionsortTestCase<STUDENT_MODEL extends TriFusion> 

       extends ProcedureTestCase<TriFusion, STUDENT_MODEL, ProcedureExecutionTrace, FusionsortProcedureDashboardModel> {


    @Override
    protected CommonTestCaseDescriptor newTestCaseDescriptor() {
        return new FusionsortTestCaseDescriptor();
    }


}
