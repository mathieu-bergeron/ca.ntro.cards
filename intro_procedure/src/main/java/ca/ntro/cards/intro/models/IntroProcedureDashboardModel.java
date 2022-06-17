package ca.ntro.cards.intro.models;

import ca.ntro.cards.intro.frontend.views.IntroProcedureDashboardView;
import ca.ntro.cards.intro.frontend.views.IntroReplayView;
import ca.ntro.cards.intro.frontend.views.IntroSelectionsView;
import ca.ntro.cards.intro.frontend.views.fragments.IntroTestCaseFragment;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.descriptor.IntroTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class IntroProcedureDashboardModel extends ProcedureDashboardModel<IntroProcedureDashboardView, 
                                                                         Intro, 
                                                                         IntroTestCaseDatabase,
                                                                         IntroTestCaseDescriptor,
                                                                         IntroReplayView,
                                                                         IntroSelectionsView,
                                                                         IntroTestCaseFragment> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
