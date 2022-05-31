package ca.ntro.cards.foo.test_cases.execution_trace;

import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface FooExecutionTrace<EXECUTABLE_MODEL extends FooCardsModel,
                                    DASHBOARD_MODEL  extends FooProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
