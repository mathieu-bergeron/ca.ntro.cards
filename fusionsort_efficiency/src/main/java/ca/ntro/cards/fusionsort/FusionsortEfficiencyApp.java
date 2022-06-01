package ca.ntro.cards.fusionsort;

import ca.ntro.cards.fusionsort.backend.FusionsortEfficiencyBackend;
import ca.ntro.cards.fusionsort.frontend.FusionsortEfficiencyFrontend;
import ca.ntro.cards.fusionsort.frontend.FusionsortEfficiencyViewData;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyDashboardView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyMessagesView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyRootView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencySettingsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortGraphsView;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortEfficiencyMessageFragment;
import ca.ntro.cards.fusionsort.models.FusionsortEfficiencyDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortEfficiencySettingsModel;
import ca.ntro.cards.fusionsort.models.FusionsortGraphsModel;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;

public abstract class   FusionsortEfficiencyApp<STUDENT_MODEL extends TriFusion>

                extends EfficiencyApp<TriFusion, 
                                      STUDENT_MODEL,
                                      FusionsortGraphsModel,
                                      FusionsortTestCase,
                                      FusionsortTestCaseDatabase,
                                      FusionsortExecutionTrace,
                                      FusionsortEfficiencyDashboardModel,
                                      FusionsortEfficiencySettingsModel,
                                      FusionsortEfficiencyBackend<STUDENT_MODEL>,
                                      FusionsortEfficiencyRootView,
                                      FusionsortGraphsView,
                                      FusionsortEfficiencyDashboardView,
                                      FusionsortEfficiencySettingsView,
                                      FusionsortEfficiencyMessagesView,
                                      FusionsortEfficiencyMessageFragment,
                                      FusionsortEfficiencyViewData,
                                      FusionsortEfficiencyFrontend> {

	@Override
	protected FusionsortEfficiencyFrontend createFrontend() {
		return new FusionsortEfficiencyFrontend();
	}

	@Override
	protected FusionsortEfficiencyBackend createBackend() {
		return new FusionsortEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<TriFusion> executableModelClass() {
		return TriFusion.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return studentClass();
	}
	
	// TODO: renommer
	protected abstract Class<STUDENT_MODEL> studentClass();

	@Override
	protected Class<FusionsortGraphsModel> canvasModelClass() {
		return FusionsortGraphsModel.class;
	}

	@Override
	protected Class<FusionsortTestCase> testCaseClass() {
		return FusionsortTestCase.class;
	}

	@Override
	protected Class<FusionsortTestCaseDatabase> testCasesModelClass() {
		return FusionsortTestCaseDatabase.class;
	}

	@Override
	protected Class<FusionsortEfficiencyDashboardModel> dashboardModelClass() {
		return FusionsortEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<FusionsortEfficiencySettingsModel> settingsModelClass() {
		return FusionsortEfficiencySettingsModel.class;
	}
}
