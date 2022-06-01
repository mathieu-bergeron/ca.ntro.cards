package ca.ntro.cards.fusionsort.models;

import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureDashboardView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortReplayView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortSelectionsView;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortTestCaseFragment;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.descriptor.FusionsortTestCaseDescriptor;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class FusionsortProcedureDashboardModel extends ProcedureDashboardModel<FusionsortProcedureDashboardView, 
                                                                         TriFusion, 
                                                                         FusionsortTestCaseDatabase,
                                                                         FusionsortTestCaseDescriptor,
                                                                         FusionsortReplayView,
                                                                         FusionsortSelectionsView,
                                                                         FusionsortTestCaseFragment> {

	@Override
	protected String defaultTestCaseId() {
		return "ex01";
	}


}
