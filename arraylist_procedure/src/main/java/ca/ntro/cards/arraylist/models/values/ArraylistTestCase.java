package ca.ntro.cards.arraylist.models.values;


import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.test_cases.descriptor.ArraylistTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   ArraylistTestCase<STUDENT_MODEL extends ListeTableau> 

       extends ProcedureTestCase<ListeTableau, STUDENT_MODEL, ProcedureExecutionTrace, ArraylistProcedureDashboardModel> {


    @Override
    protected CommonTestCaseDescriptor newTestCaseDescriptor() {
        return new ArraylistTestCaseDescriptor();
    }


}
