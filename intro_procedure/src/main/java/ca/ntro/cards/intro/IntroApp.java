package ca.ntro.cards.intro;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.intro.backend.IntroProcedureBackend;
import ca.ntro.cards.intro.frontend.IntroProcedureFrontend;
import ca.ntro.cards.intro.frontend.IntroProcedureViewData;
import ca.ntro.cards.intro.frontend.views.IntroCardsView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureMessagesView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureDashboardView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureRootView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureSettingsView;
import ca.ntro.cards.intro.frontend.views.fragments.IntroProcedureMessageFragment;
import ca.ntro.cards.intro.messages.IntroMsgAcceptManualModel;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.descriptor.IntroTestCaseDescriptor;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.intro.models.IntroProcedureSettingsModel;

public abstract class   IntroApp<STUDENT_MODEL extends Intro>

                extends ProcedureApp<Intro,           // executable model
                                     STUDENT_MODEL,
                                     STUDENT_MODEL,     // canvas model
                                     IntroTestCase,
                                     IntroTestCaseDescriptor,
                                     IntroTestCaseDatabase,
                                     IntroExecutionTrace,
                                     IntroProcedureDashboardModel,
                                     IntroProcedureSettingsModel,
                                     IntroMsgAcceptManualModel,
                                     IntroProcedureBackend<STUDENT_MODEL>,
                                     IntroProcedureRootView,
                                     IntroCardsView,
                                     IntroProcedureDashboardView,
                                     IntroProcedureSettingsView,
                                     IntroProcedureMessagesView,
                                     IntroProcedureMessageFragment,
                                     IntroProcedureViewData,
                                     IntroProcedureFrontend<STUDENT_MODEL>> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	
	@Override
	protected Class<Intro> executableModelClass() {
		return Intro.class;
	}

	// TODO: renommer
	protected abstract Class<STUDENT_MODEL> studentClass();

	@Override
	protected Class<IntroTestCase> testCaseClass() {
		return IntroTestCase.class;
	}

	@Override
	protected Class<IntroTestCaseDatabase> testCasesModelClass() {
		return IntroTestCaseDatabase.class;
	}


	@Override
	protected Class<IntroProcedureDashboardModel> dashboardModelClass() {
		return IntroProcedureDashboardModel.class;
	}


	@Override
	protected Class<IntroProcedureSettingsModel> settingsModelClass() {
		return IntroProcedureSettingsModel.class;
	}

	@Override
	protected IntroProcedureFrontend createFrontend() {
		return new IntroProcedureFrontend();
	}


	@Override
	protected IntroProcedureBackend createBackend() {
		return new IntroProcedureBackend();
	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class<STUDENT_MODEL> canvasModelClass() {
		return (Class<STUDENT_MODEL>) studentClass();
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return studentClass();
	}

	@Override
	protected Class<IntroTestCaseDescriptor> testCaseDescriptorClass() {
		return IntroTestCaseDescriptor.class;
	}

	@Override
	protected Class<IntroMsgAcceptManualModel> msgAcceptManualModelClass() {
		return IntroMsgAcceptManualModel.class;
	}

	@Override
	protected String initialTestCaseId() {
		return "ex01";
	}

}
