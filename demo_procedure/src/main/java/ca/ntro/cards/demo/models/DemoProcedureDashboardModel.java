package ca.ntro.cards.demo.models;

import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.demo.frontend.views.DemoProcedureDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoReplayView;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class DemoProcedureDashboardModel extends ProcedureDashboardModel<DemoProcedureDashboardView, 
                                                                         DemoCardsModel, 
                                                                         DemoTestCaseDatabase,
                                                                         DemoReplayView> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
