package ca.ntro.cards.common.test_cases.execution_trace;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;

public interface ExecutionTrace <EXECUTABLE_MODEL extends CommonExecutableModel> 

       extends   Value, Serializable {

	void pushReferenceTo(EXECUTABLE_MODEL model);
	void pushCopyOf(EXECUTABLE_MODEL model);
	void updateDashboard(CommonDashboardModel dashboardModel);

}
