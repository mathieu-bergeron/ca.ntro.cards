package ca.ntro.cards.freesort.test_cases.execution_trace;

import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public class FreesortExecutionTraceFull<EXECUTABLE_MODEL extends TriLibre,
                                    DASHBOARD_MODEL  extends FreesortProcedureDashboardModel> 

       extends ProcedureExecutionTraceFull<EXECUTABLE_MODEL, 
                                           DASHBOARD_MODEL>

       implements FreesortExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

}
