package ca.ntro.cards.common.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.test_cases.CommonTestCase;

public abstract class CommonDashboardModel<DASHBOARD_VIEW  extends CommonDashboardView>

       implements Model {
	
	private long numberOfSteps = 0;
	private int currentStep = 0;
	private int numberOfCards = 0;
	
	private String currentTestCase;

	private List<String> testCases = new ArrayList<>();
	

	public long getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(long numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	public void incrementSimpleOperations() {
		numberOfSteps++;
	}

	public List<String> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<String> testCases) {
		this.testCases = testCases;
	}

	public void displayOn(DASHBOARD_VIEW dashboardView) {
		dashboardView.clearTestCases();
		for(String testCase : testCases) {
			dashboardView.addTestCase(testCase);
		}

		//dashboardView.displayNumberOfCards(numberOfCards);
		//dashboardView.displayCurrentStep(currentStep);
		//dashboardView.displayNumberOfSteps(numberOfSteps);
	}

	public void initialize() {
		numberOfSteps = 0;
	}

	public void addTestCase(CommonTestCase testCase) {

	}

	public void addTestCaseId(String testCaseId) {
		testCases.add(testCaseId);
	}

}
