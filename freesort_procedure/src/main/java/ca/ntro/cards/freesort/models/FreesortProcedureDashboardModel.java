package ca.ntro.cards.freesort.models;

import ca.ntro.cards.freesort.frontend.views.FreesortProcedureDashboardView;
import ca.ntro.cards.freesort.frontend.views.FreesortReplayView;
import ca.ntro.cards.freesort.frontend.views.FreesortSelectionsView;
import ca.ntro.cards.freesort.frontend.views.FreesortVariablesView;
import ca.ntro.cards.freesort.frontend.views.fragments.FreesortTestCaseFragment;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.descriptor.FreesortTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class FreesortProcedureDashboardModel extends ProcedureDashboardModel<FreesortProcedureDashboardView, 
                                                                         TriLibre, 
                                                                         FreesortTestCaseDatabase,
                                                                         FreesortTestCaseDescriptor,
                                                                         FreesortReplayView,
                                                                         FreesortSelectionsView,
                                                                         FreesortTestCaseFragment> {


	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}

}
