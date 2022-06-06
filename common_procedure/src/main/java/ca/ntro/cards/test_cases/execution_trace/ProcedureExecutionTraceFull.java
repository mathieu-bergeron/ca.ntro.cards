package ca.ntro.cards.test_cases.execution_trace;

import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTraceFull;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;

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
	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		dashboardModel.setCurrentStep(getCurrent()+1);
		dashboardModel.setCurrentOutputSize(getTrace().size());
	}



}
