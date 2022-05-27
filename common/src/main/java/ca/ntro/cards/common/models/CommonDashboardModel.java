package ca.ntro.cards.common.models;


import ca.ntro.app.models.Model;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;

public abstract class CommonDashboardModel<DASHBOARD_VIEW  extends CommonDashboardView>

       implements Model {


	public abstract void addOrUpdateTestCase(TestCaseDescriptor testCaseDescriptor);

	public abstract void displayOn(DASHBOARD_VIEW dashboardView);

}
