package ca.ntro.cards.common.test_cases.execution_trace;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

public class ExecutionTraceSizeOnly<EXECUTABLE_MODEL extends CommonExecutableModel,
                                    DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements ExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {
    	   
    	   private int size = 0;
    	   

	@Override
	public void pushReferenceTo(EXECUTABLE_MODEL model) {
		size++;
	}

	@Override
	public void pushCopyOf(EXECUTABLE_MODEL model) {
		size++;
	}

	@Override
	public EXECUTABLE_MODEL firstModel() {
		return null;
	}

	@Override
	public void copyDataTo(EXECUTABLE_MODEL model) {
	}

	@Override
	public void stepForward() {
	}

	@Override
	public void stepBackward() {
	}

	@Override
	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		dashboardModel.setNumberOfSteps(size);
	}

}
