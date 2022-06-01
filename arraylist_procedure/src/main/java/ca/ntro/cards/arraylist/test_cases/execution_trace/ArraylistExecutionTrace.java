package ca.ntro.cards.arraylist.test_cases.execution_trace;

import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface ArraylistExecutionTrace<EXECUTABLE_MODEL extends ListeTableau,
                                    DASHBOARD_MODEL  extends ArraylistProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
