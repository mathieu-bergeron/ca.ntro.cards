package ca.ntro.cards.test_cases.execution_trace;

import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.values.ComparisonReport;

public class ProcedureExecutionTraceFull<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                         DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonExecutionTraceFull<EXECUTABLE_MODEL, 
                                        DASHBOARD_MODEL> 

       implements ProcedureExecutionTrace<EXECUTABLE_MODEL,
                                          DASHBOARD_MODEL> {

	@SuppressWarnings("unchecked")
	@Override
	public void copyCurrentModelInto(EXECUTABLE_MODEL model) {
		model.copyDataFrom(currentModel());
	}

	@Override
	public ComparisonReport compareToSolution(ProcedureExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> solutionTrace) {
		return solutionTrace.evaluateCandidateSolution(lastModel());
	}

	@Override
	public ComparisonReport evaluateCandidateSolution(EXECUTABLE_MODEL candidateSolution) {
		EXECUTABLE_MODEL solution = lastModel();

		return candidateSolution.compareToSolution(solution);
	}






}
