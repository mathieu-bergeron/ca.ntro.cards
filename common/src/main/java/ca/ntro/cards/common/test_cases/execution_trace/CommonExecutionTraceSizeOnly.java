package ca.ntro.cards.common.test_cases.execution_trace;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.common.test_cases.descriptor.CommonAttemptDescriptor;

public class CommonExecutionTraceSizeOnly<EXECUTABLE_MODEL extends CommonExecutableModel,
                                          DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> {
    	   
    	   private int size = 0;
    	   

	@Override
	public void pushReferenceTo(EXECUTABLE_MODEL model) {
		size++;
	}

	@Override
	public void pushCloneOf(EXECUTABLE_MODEL model) {
		size++;
	}

	@Override
	public void copyCurrentModelInto(EXECUTABLE_MODEL model) {
	}

	@Override
	public void copyInitialModelInto(EXECUTABLE_MODEL target) {
	}

	@Override
	public void stepForward() {
	}

	@Override
	public void stepBackward() {
	}

	@Override
	public int numberOfSteps() {
		return size;
	}

	@Override
	public void truncateAfterCurrentStep() {
	}

	@Override
	public void rewindToFirstStep() {
	}

	@Override
	public void fastForwardToLastStep() {
	}

	@Override
	public CommonAttemptDescriptor asAttemptDescriptor() {
		return null;
	}


}
