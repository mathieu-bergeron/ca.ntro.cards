package ca.ntro.cards.arraylist.models;

import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureDashboardView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistReplayView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistSelectionsView;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistTestCaseFragment;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.descriptor.ArraylistTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class ArraylistProcedureDashboardModel extends ProcedureDashboardModel<ArraylistProcedureDashboardView, 
                                                                         ArraylistCardsModel, 
                                                                         ArraylistTestCaseDatabase,
                                                                         ArraylistTestCaseDescriptor,
                                                                         ArraylistReplayView,
                                                                         ArraylistSelectionsView,
                                                                         ArraylistTestCaseFragment> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
