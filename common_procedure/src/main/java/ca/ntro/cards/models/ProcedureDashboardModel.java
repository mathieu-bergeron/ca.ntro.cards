package ca.ntro.cards.models;


import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureReplayView;
import ca.ntro.cards.frontend.views.ProcedureSelectionsView;
import ca.ntro.cards.frontend.views.fragments.ProcedureTestCaseFragment;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;

public abstract class ProcedureDashboardModel<DASHBOARD_VIEW     extends ProcedureDashboardView,
								              CARDS_MODEL        extends ProcedureCardsModel, 
                                              TEST_CASE_DATABASE extends ProcedureTestCaseDatabase,
                                              TEST_CASE          extends ProcedureTestCaseDescriptor,
                                              REPLAY_VIEW        extends ProcedureReplayView,
                                              SELECTIONS_VIEW    extends ProcedureSelectionsView,
                                              TEST_CASE_FRAGMENT extends ProcedureTestCaseFragment>

       extends CommonDashboardModel<DASHBOARD_VIEW, TEST_CASE> {
    	   
    private TestCaseById<CARDS_MODEL, TEST_CASE> byId = new TestCaseById<>();
    private TestCasesByCategory<CARDS_MODEL, TEST_CASE> byCategory = new TestCasesByCategory<>();

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

	public TestCaseById<CARDS_MODEL, TEST_CASE> getById() {
		return byId;
	}

	public void setById(TestCaseById<CARDS_MODEL, TEST_CASE> byId) {
		this.byId = byId;
	}

	public TestCasesByCategory<CARDS_MODEL, TEST_CASE> getByCategory() {
		return byCategory;
	}

	public void setByCategory(TestCasesByCategory<CARDS_MODEL, TEST_CASE> byCategory) {
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
	}

	@Override
	public void addOrUpdateTestCase(TEST_CASE testCaseDescriptor) {
		byId.addTestCase(testCaseDescriptor);
		byCategory.addTestCase(testCaseDescriptor);
	}
	
	public void changeCurrentTestCase(String testCaseId) {
		this.currentTestCaseId = currentTestCaseId;
		this.currentStep = byId.testCaseById(testCaseId).currentStep(Mode.MANUAL);
		this.currentInputSize = byId.testCaseById(testCaseId).inputSize();
		this.currentOutputSize = byId.testCaseById(testCaseId).numberOfSteps(Mode.MANUAL);
	}
	
	public void displayOn(SELECTIONS_VIEW selectionsView, 
			              ProcedureDashboardModel previousModel,
			              ViewLoader<TEST_CASE_FRAGMENT> testCaseFragmentLoader) {
		
		if(numberOfTestCases() > previousModel.numberOfTestCases()) {
			
			byCategory.inOrder().reduceToResult(0, (testCaseIndex, testCaseDescriptor) -> {
				
				if(!previousModel.containsTestCase(testCaseDescriptor)) {

					TEST_CASE_FRAGMENT testCaseFragment = testCaseFragmentLoader.createView();
					
					testCaseDescriptor.displayOn(testCaseFragment);

					selectionsView.insertTestCase(testCaseIndex, testCaseFragment);
				}
				
				return testCaseIndex+1;
			});
			
		}
		
	}

	public int numberOfTestCases() {
		return byId.size();
	}

	public boolean containsTestCase(TEST_CASE testCaseDescriptor) {
		return containsTestCase(testCaseDescriptor.getTestCaseId());
	}

	public boolean containsTestCase(String testCaseId) {
		return byId.testCaseById(testCaseId) != null;
	}

}

