package ca.ntro.cards.common.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.test_cases.CommonTestCase;

public abstract class CommonDashboardModel<DASHBOARD_VIEW  extends CommonDashboardView>

       implements Model {
	
	private List<String> testCases = new ArrayList<>();
	
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

	public void addTestCase(CommonTestCase testCase) {

	}

	public void addTestCaseId(String testCaseId) {
		testCases.add(testCaseId);
	}

}
