package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.enums.Attempt;
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

	public void updateCardsModel(Attempt attempt, EXECUTABLE_MODEL cardsModel) {
		executionTraceByMode(attempt).copyCurrentModelInto(cardsModel);
	}

	public void pushManualExecutionStep(STUDENT_MODEL model) {
		executionTraceByMode(Attempt.MANUAL).truncateAfterCurrentStep();
		executionTraceByMode(Attempt.MANUAL).pushCloneOf(model);
		executionTraceByMode(Attempt.MANUAL).stepForward();
	}

}
