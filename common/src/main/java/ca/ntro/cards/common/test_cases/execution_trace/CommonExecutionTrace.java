package ca.ntro.cards.common.test_cases.execution_trace;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.descriptor.AbstractAttemptDescriptor;

public interface CommonExecutionTrace<EXECUTABLE_MODEL extends CommonExecutableModel,
                                      DASHBOARD_MODEL  extends CommonDashboardModel> 

       extends   Value, Serializable {

	void pushReferenceTo(EXECUTABLE_MODEL model);
	void pushCloneOf(EXECUTABLE_MODEL model);
	
	int numberOfSteps();

	void stepForward();
	void stepBackward();

	void copyInitialModelInto(EXECUTABLE_MODEL target);
	void copyCurrentModelInto(EXECUTABLE_MODEL target);

	void truncateAfterCurrentStep();
	void rewindToFirstStep();
	void fastForwardToLastStep();

	AbstractAttemptDescriptor asAttemptDescriptor();

}
