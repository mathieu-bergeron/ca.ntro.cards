package ca.ntro.cards.intro.test_cases.execution_trace;

import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public class IntroExecutionTraceFull<EXECUTABLE_MODEL extends Intro,
                                    DASHBOARD_MODEL  extends IntroProcedureDashboardModel> 

       extends ProcedureExecutionTraceFull<EXECUTABLE_MODEL, 
                                           DASHBOARD_MODEL>

       implements IntroExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

}
