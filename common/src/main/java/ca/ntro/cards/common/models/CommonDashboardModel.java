package ca.ntro.cards.common.models;


import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;

public abstract class CommonDashboardModel<DASHBOARD_VIEW     extends CommonDashboardView,
                                           TEST_CASE          extends AbstractTestCaseDescriptor,
                                           TEST_CASE_DATABASE extends CommonTestCaseDatabase>

       implements Model {


	public abstract void addOrUpdateTestCase(TEST_CASE testCaseDescriptor);

	public abstract void displayOn(DASHBOARD_VIEW dashboardView);

	public abstract void loadDbFromDir(TEST_CASE_DATABASE testCaseDatabase);

}
