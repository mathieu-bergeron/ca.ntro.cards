package ca.ntro.cards.naivesort.test_cases.execution_trace;

import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public class NaivesortExecutionTraceFull<EXECUTABLE_MODEL extends TriNaif,
                                    DASHBOARD_MODEL  extends NaivesortProcedureDashboardModel> 

       extends ProcedureExecutionTraceFull<EXECUTABLE_MODEL, 
                                           DASHBOARD_MODEL>

       implements NaivesortExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

}
