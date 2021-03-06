package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class  ProcedureTestCase<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                         STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                         EXECUTION_TRACE  extends ProcedureExecutionTrace,
                                         DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonTestCase<EXECUTABLE_MODEL, 
                              STUDENT_MODEL, 
                              EXECUTION_TRACE,
                              DASHBOARD_MODEL> {

	public void updateCardsModel(EXECUTABLE_MODEL cardsModel) {
		executionTraceByMode(Mode.MANUAL).copyCurrentModelInto(cardsModel);
	}

	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		executionTraceByMode(Mode.MANUAL).updateDashboardModel(dashboardModel);
	}

	public void pushManualExecutionStep(STUDENT_MODEL model) {
		executionTraceByMode(Mode.MANUAL).truncateAfterCurrentStep();
		executionTraceByMode(Mode.MANUAL).pushCloneOf(model);
		executionTraceByMode(Mode.MANUAL).stepForward();
	}

}
