package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;

public abstract class ProcedureTestCaseDatabase<EXECUTABLE_MODEL extends CommonExecutableModel, 
                                                STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                                TEST_CASE        extends ProcedureTestCase,
                                                DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonTestCaseDatabase<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> {


	public void updateCardsModel(ProcedureCardsModel cardsModel) {
		updateCardsModel(getCurrentTestCaseId(), cardsModel);
	}

	public void updateCardsModel(String testCaseId, ProcedureCardsModel cardsModel) {
		TEST_CASE testCase = testCaseById(testCaseId);

		testCase.updateCardsModel(cardsModel);
	}

	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		TEST_CASE testCase = testCaseById(getCurrentTestCaseId());

		testCase.updateDashboardModel(dashboardModel);
	}


}
