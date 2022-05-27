package ca.ntro.cards.common.test_cases.execution_trace;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

public interface CommonExecutionTrace<EXECUTABLE_MODEL extends CommonExecutableModel,
                                      DASHBOARD_MODEL  extends CommonDashboardModel> 

       extends   Value, Serializable {

	void pushReferenceTo(EXECUTABLE_MODEL model);
	void pushCloneOf(EXECUTABLE_MODEL model);

	int numberOfSteps();

	void stepForward();
	void stepBackward();

	void copyCurrentModelInto(EXECUTABLE_MODEL model);

	void updateDashboardModel(DASHBOARD_MODEL dashboardModel);


}
