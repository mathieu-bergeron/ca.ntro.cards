package ca.ntro.cards.foo.models.values;


import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class   FooTestCase<STUDENT_MODEL extends FooCardsModel> 

       extends ProcedureTestCase<FooCardsModel, STUDENT_MODEL, ProcedureExecutionTrace, FooProcedureDashboardModel> {

}
