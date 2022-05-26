package ca.ntro.cards.demo.models;

import ca.ntro.cards.demo.frontend.views.DemoProcedureDashboardView;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class DemoProcedureDashboardModel extends ProcedureDashboardModel<DemoProcedureDashboardView, TriNaif, DemoTestCaseDatabase> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}

}
