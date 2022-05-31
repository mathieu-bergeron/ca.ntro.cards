package ca.ntro.cards.foo.test_cases.execution_trace;

import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public class FooExecutionTraceFull<EXECUTABLE_MODEL extends FooCardsModel,
                                    DASHBOARD_MODEL  extends FooProcedureDashboardModel> 

       extends ProcedureExecutionTraceFull<EXECUTABLE_MODEL, 
                                           DASHBOARD_MODEL>

       implements FooExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

}
