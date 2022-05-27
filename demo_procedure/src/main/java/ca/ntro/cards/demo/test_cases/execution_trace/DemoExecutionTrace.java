package ca.ntro.cards.demo.test_cases.execution_trace;

import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface DemoExecutionTrace<EXECUTABLE_MODEL extends DemoCardsModel,
                                    DASHBOARD_MODEL  extends DemoProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
