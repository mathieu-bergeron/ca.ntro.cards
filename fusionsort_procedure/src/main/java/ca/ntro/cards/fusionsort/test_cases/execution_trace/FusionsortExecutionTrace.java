package ca.ntro.cards.fusionsort.test_cases.execution_trace;

import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public interface FusionsortExecutionTrace<EXECUTABLE_MODEL extends TriFusion,
                                    DASHBOARD_MODEL  extends FusionsortProcedureDashboardModel> 

       extends ProcedureExecutionTrace<EXECUTABLE_MODEL, 
                                       DASHBOARD_MODEL> {

}
