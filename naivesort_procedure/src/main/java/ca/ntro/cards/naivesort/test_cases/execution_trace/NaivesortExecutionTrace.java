package ca.ntro.cards.naivesort.test_cases.execution_trace;

import ca.ntro.cards.naivesort.models.NaivesortCardsModel;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface NaivesortExecutionTrace<EXECUTABLE_MODEL extends NaivesortCardsModel,
                                    DASHBOARD_MODEL  extends NaivesortProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
