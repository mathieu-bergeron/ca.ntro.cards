package ca.ntro.cards.intro;

import ca.ntro.cards.intro.backend.IntroEfficiencyBackend;
import ca.ntro.cards.intro.frontend.IntroEfficiencyFrontend;
import ca.ntro.cards.intro.frontend.IntroEfficiencyViewData;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencyDashboardView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencyMessagesView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencyRootView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencySettingsView;
import ca.ntro.cards.intro.frontend.views.IntroGraphsView;
import ca.ntro.cards.intro.frontend.views.fragments.IntroEfficiencyMessageFragment;
import ca.ntro.cards.intro.models.IntroEfficiencyDashboardModel;
import ca.ntro.cards.intro.models.IntroEfficiencySettingsModel;
import ca.ntro.cards.intro.models.IntroGraphsModel;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.efficiency.EfficiencyApp;

public abstract class   IntroEfficiencyApp<STUDENT_MODEL extends Intro>

                extends EfficiencyApp<Intro, 
                                      STUDENT_MODEL,
                                      IntroGraphsModel,
                                      IntroTestCase,
                                      IntroTestCaseDatabase,
                                      IntroExecutionTrace,
                                      IntroEfficiencyDashboardModel,
                                      IntroEfficiencySettingsModel,
                                      IntroEfficiencyBackend<STUDENT_MODEL>,
                                      IntroEfficiencyRootView,
                                      IntroGraphsView,
                                      IntroEfficiencyDashboardView,
                                      IntroEfficiencySettingsView,
                                      IntroEfficiencyMessagesView,
                                      IntroEfficiencyMessageFragment,
                                      IntroEfficiencyViewData,
                                      IntroEfficiencyFrontend> {

	@Override
	protected IntroEfficiencyFrontend createFrontend() {
		return new IntroEfficiencyFrontend();
	}

	@Override
	protected IntroEfficiencyBackend createBackend() {
		return new IntroEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<Intro> executableModelClass() {
		return Intro.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return studentClass();
	}
	
	// TODO: renommer
	protected abstract Class<STUDENT_MODEL> studentClass();

	@Override
	protected Class<IntroGraphsModel> canvasModelClass() {
		return IntroGraphsModel.class;
	}

	@Override
	protected Class<IntroTestCase> testCaseClass() {
		return IntroTestCase.class;
	}

	@Override
	protected Class<IntroTestCaseDatabase> testCasesModelClass() {
		return IntroTestCaseDatabase.class;
	}

	@Override
	protected Class<IntroEfficiencyDashboardModel> dashboardModelClass() {
		return IntroEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<IntroEfficiencySettingsModel> settingsModelClass() {
		return IntroEfficiencySettingsModel.class;
	}

	@Override
	protected String initialTestCaseId() {
		return "ex01";
	}

	@Override
	protected Attempt initialAttempt() {
		return Attempt.SOLUTION;
	}
}
