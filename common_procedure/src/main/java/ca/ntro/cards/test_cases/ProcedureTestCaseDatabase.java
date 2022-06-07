package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class ProcedureTestCaseDatabase<EXECUTABLE_MODEL extends ProcedureCardsModel, 
                                                STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                                TEST_CASE        extends ProcedureTestCase,
                                                EXECUTION_TRACE  extends ProcedureExecutionTrace,
                                                DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonTestCaseDatabase<EXECUTABLE_MODEL, 
                                      STUDENT_MODEL, 
                                      TEST_CASE,
                                      EXECUTION_TRACE> {


	public void updateCardsModel(String testCaseId, Attempt attempt, ProcedureCardsModel cardsModel) {
		TEST_CASE testCase = testCaseById(testCaseId);

		testCase.updateCardsModel(attempt, cardsModel);
	}

	public void addOrUpdateTestCases(DASHBOARD_MODEL dashboardModel) {
		testCases().forEach(testCase -> {
			
			dashboardModel.addOrUpdateTestCase((ProcedureTestCaseDescriptor) testCase.asTestCaseDescriptor());

		});
	}

	public void addOrUpdateTestCase(String testCaseId, DASHBOARD_MODEL dashboardModel) {
		TEST_CASE testCase = testCaseById(testCaseId);

		dashboardModel.addOrUpdateTestCase((ProcedureTestCaseDescriptor) testCase.asTestCaseDescriptor());
	}

	public void pushManualExecutionStep(String testCaseId, STUDENT_MODEL cardsModel) {
		TEST_CASE testCase = testCaseById(testCaseId);

		testCase.pushManualExecutionStep(cardsModel);
	}



}
