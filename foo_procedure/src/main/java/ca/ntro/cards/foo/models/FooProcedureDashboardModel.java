package ca.ntro.cards.foo.models;

import ca.ntro.cards.foo.frontend.views.FooProcedureDashboardView;
import ca.ntro.cards.foo.frontend.views.FooReplayView;
import ca.ntro.cards.foo.frontend.views.FooSelectionsView;
import ca.ntro.cards.foo.frontend.views.fragments.FooTestCaseFragment;
import ca.ntro.cards.foo.test_cases.FooTestCaseDatabase;
import ca.ntro.cards.foo.test_cases.descriptor.FooTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class FooProcedureDashboardModel extends ProcedureDashboardModel<FooProcedureDashboardView, 
                                                                         FooCardsModel, 
                                                                         FooTestCaseDatabase,
                                                                         FooTestCaseDescriptor,
                                                                         FooReplayView,
                                                                         FooSelectionsView,
                                                                         FooTestCaseFragment> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
