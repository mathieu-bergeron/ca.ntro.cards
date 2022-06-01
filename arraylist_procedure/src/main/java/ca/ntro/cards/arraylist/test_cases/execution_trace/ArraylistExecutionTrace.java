package ca.ntro.cards.arraylist.test_cases.execution_trace;

import ca.ntro.cards.arraylist.models.ArraylistCardsModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface ArraylistExecutionTrace<EXECUTABLE_MODEL extends ArraylistCardsModel,
                                    DASHBOARD_MODEL  extends ArraylistProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
