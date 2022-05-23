package ca.ntro.cards.common.models.values.execution_trace;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

public class ExecutionTraceSizeOnly<EXECUTABLE_MODEL extends CommonExecutableModel> 

       implements ExecutionTrace<EXECUTABLE_MODEL> {
    	   
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
	public void updateDashboard(CommonDashboardModel dashboardModel) {
		dashboardModel.setNumberOfSteps(size);
	}

}
