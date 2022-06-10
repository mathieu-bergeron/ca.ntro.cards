package ca.ntro.cards.efficiency.test_cases.execution_trace;

import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceSizeOnly;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.values.ComparisonReport;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class EfficiencyExecutionTraceSizeOnly<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                              DASHBOARD_MODEL  extends ProcedureDashboardModel> 

      extends CommonExecutionTraceSizeOnly<EXECUTABLE_MODEL, DASHBOARD_MODEL>

      implements ProcedureExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {

	@Override
	public ComparisonReport compareToSolution(ProcedureExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> executionTraceByMode) {
		return null;
	}

	@Override
	public ComparisonReport evaluateCandidateSolution(EXECUTABLE_MODEL lastModel) {
		return null;
	}

}
