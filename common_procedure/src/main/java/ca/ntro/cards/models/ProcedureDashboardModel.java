package ca.ntro.cards.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureReplayView;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;

public abstract class ProcedureDashboardModel<DASHBOARD_VIEW  extends ProcedureDashboardView,
								              CARDS_MODEL extends ProcedureCardsModel, 
                                              TEST_CASE_DATABASE extends ProcedureTestCaseDatabase,
                                              REPLAY_VIEW extends ProcedureReplayView>

       extends CommonDashboardModel<DASHBOARD_VIEW> {
    	   
    private TestCaseById<CARDS_MODEL, TestCaseDescriptor> byId = new TestCaseById<>();
    private TestCasesByCategory<CARDS_MODEL, TestCaseDescriptor> byCategory = new TestCasesByCategory<>();

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

	public TestCaseById<CARDS_MODEL, TestCaseDescriptor> getById() {
		return byId;
	}

	public void setById(TestCaseById<CARDS_MODEL, TestCaseDescriptor> byId) {
		this.byId = byId;
	}

	public TestCasesByCategory<CARDS_MODEL, TestCaseDescriptor> getByCategory() {
		return byCategory;
	}

	public void setByCategory(TestCasesByCategory<CARDS_MODEL, TestCaseDescriptor> byCategory) {
		this.byCategory = byCategory;
	}

	protected abstract String defaultTestCaseId();

	public void loadCurrentTestCase(TEST_CASE_DATABASE testCaseDatabase) {
		testCaseDatabase.loadTestCase(currentTestCaseId);
	}

	public void updateCardsModel(TEST_CASE_DATABASE testCaseDatabase, CARDS_MODEL cardsModel) {
		testCaseDatabase.updateCardsModel(currentTestCaseId, cardsModel);
	}

	public void displayOn(REPLAY_VIEW replayView) {
		replayView.displayNumberOfCards(String.valueOf(currentInputSize));
		replayView.displayCurrentStep(String.valueOf(currentStep));
		replayView.displayNumberOfSteps(String.valueOf(currentOutputSize));
	}

	@Override
	public void displayOn(DASHBOARD_VIEW dashboardView) {
		dashboardView.clearTestCases();
		byCategory.inOrder().forEach(testCase -> {
			dashboardView.addTestCase(testCase.testCaseId());
		});
	}


	@Override
	public void addOrUpdateTestCase(TestCaseDescriptor testCaseDescriptor) {
		byId.addTestCase(testCaseDescriptor);
		byCategory.addTestCase(testCaseDescriptor);
	}

}

