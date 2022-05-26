package ca.ntro.cards.models;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;

public abstract class ProcedureDashboardModel<DASHBOARD_VIEW  extends ProcedureDashboardView,
								              CARDS_MODEL extends ProcedureCardsModel, 
                                              TEST_CASE_DATABASE extends ProcedureTestCaseDatabase>

       extends CommonDashboardModel<DASHBOARD_VIEW> {
	
	private String currentTestCaseId = defaultTestCaseId();

	public String getCurrentTestCaseId() {
		return currentTestCaseId;
	}

	public void setCurrentTestCaseId(String currentTestCaseId) {
		this.currentTestCaseId = currentTestCaseId;
	}

	protected abstract String defaultTestCaseId();

	public void loadCurrentTestCase(TEST_CASE_DATABASE testCaseDatabase) {
		testCaseDatabase.loadTestCase(currentTestCaseId);
	}

	public void updateCardsModel(TEST_CASE_DATABASE testCaseDatabase, CARDS_MODEL cardsModel) {
		testCaseDatabase.updateCardsModel(currentTestCaseId, cardsModel);
	}

}

