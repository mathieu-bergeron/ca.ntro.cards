package ca.ntro.cards.naivesort.models;

import ca.ntro.cards.naivesort.frontend.views.NaivesortProcedureDashboardView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortReplayView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortSelectionsView;
import ca.ntro.cards.naivesort.frontend.views.fragments.NaivesortTestCaseFragment;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.descriptor.NaivesortTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class NaivesortProcedureDashboardModel extends ProcedureDashboardModel<NaivesortProcedureDashboardView, 
                                                                         TriNaif, 
                                                                         NaivesortTestCaseDatabase,
                                                                         NaivesortTestCaseDescriptor,
                                                                         NaivesortReplayView,
                                                                         NaivesortSelectionsView,
                                                                         NaivesortTestCaseFragment> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
