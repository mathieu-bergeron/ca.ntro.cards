package ca.ntro.cards.models;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureReplayView;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;

public abstract class ProcedureDashboardModel<DASHBOARD_VIEW  extends ProcedureDashboardView,
								              CARDS_MODEL extends ProcedureCardsModel, 
                                              TEST_CASE_DATABASE extends ProcedureTestCaseDatabase,
                                              REPLAY_VIEW extends ProcedureReplayView>

       extends CommonDashboardModel<DASHBOARD_VIEW> {
	
	private Mode currentMode;
	private String currentTestCaseId = defaultTestCaseId();
	private int currentStep;
	private int currentInputSize;
	private int currentOutputSize;

	public String getCurrentTestCaseId() {
		return currentTestCaseId;
	}

	public void setCurrentTestCaseId(String currentTestCaseId) {
		this.currentTestCaseId = currentTestCaseId;
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(Mode currentMode) {
		this.currentMode = currentMode;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public int getCurrentInputSize() {
		return currentInputSize;
	}

	public void setCurrentInputSize(int currentInputSize) {
		this.currentInputSize = currentInputSize;
	}

	public int getCurrentOutputSize() {
		return currentOutputSize;
	}

	public void setCurrentOutputSize(int currentOutputSize) {
		this.currentOutputSize = currentOutputSize;
	}

	protected abstract String defaultTestCaseId();

	public void loadCurrentTestCase(TEST_CASE_DATABASE testCaseDatabase) {
		testCaseDatabase.loadTestCase(currentTestCaseId);
	}

	public void updateCardsModel(TEST_CASE_DATABASE testCaseDatabase, CARDS_MODEL cardsModel) {
		testCaseDatabase.updateCardsModel(currentTestCaseId, cardsModel);
	}

	public void displayOn(REPLAY_VIEW replayView) {
		replayView.displayNumberOfCards(String.valueOf(getNumberOfCards()));
		replayView.displayCurrentStep(String.valueOf(currentStep));
		replayView.displayNumberOfSteps(String.valueOf(getNumberOfSteps()));
	}

}

