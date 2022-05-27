package ca.ntro.cards.test_cases.execution_trace;

import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;

public interface ProcedureExecutionTrace<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                         DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonExecutionTrace<EXECUTABLE_MODEL, 
                                    DASHBOARD_MODEL> {

}
