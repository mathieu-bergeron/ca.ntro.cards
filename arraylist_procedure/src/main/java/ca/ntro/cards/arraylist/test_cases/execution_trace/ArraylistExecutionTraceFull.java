package ca.ntro.cards.arraylist.test_cases.execution_trace;

import ca.ntro.cards.arraylist.models.ArraylistCardsModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public class ArraylistExecutionTraceFull<EXECUTABLE_MODEL extends ArraylistCardsModel,
                                    DASHBOARD_MODEL  extends ArraylistProcedureDashboardModel> 

       extends ProcedureExecutionTraceFull<EXECUTABLE_MODEL, 
                                           DASHBOARD_MODEL>

       implements ArraylistExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

}
