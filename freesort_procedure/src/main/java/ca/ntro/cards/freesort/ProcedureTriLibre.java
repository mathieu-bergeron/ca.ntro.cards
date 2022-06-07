package ca.ntro.cards.freesort;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.freesort.backend.FreesortProcedureBackend;
import ca.ntro.cards.freesort.frontend.FreesortProcedureFrontend;
import ca.ntro.cards.freesort.frontend.FreesortProcedureViewData;
import ca.ntro.cards.freesort.frontend.views.FreesortCardsView;
import ca.ntro.cards.freesort.frontend.views.FreesortProcedureDashboardView;
import ca.ntro.cards.freesort.frontend.views.FreesortProcedureMessagesView;
import ca.ntro.cards.freesort.frontend.views.FreesortProcedureRootView;
import ca.ntro.cards.freesort.frontend.views.FreesortProcedureSettingsView;
import ca.ntro.cards.freesort.frontend.views.fragments.FreesortProcedureMessageFragment;
import ca.ntro.cards.freesort.messages.FreesortMsgAcceptManualModel;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.descriptor.FreesortTestCaseDescriptor;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;
import ca.ntro.cards.freesort.models.FreesortProcedureSettingsModel;

public abstract class   ProcedureTriLibre<STUDENT_MODEL extends TriLibre>

                extends ProcedureApp<TriLibre,           // executable model
                                     STUDENT_MODEL,
                                     STUDENT_MODEL,     // canvas model
                                     FreesortTestCase,
                                     FreesortTestCaseDescriptor,
                                     FreesortTestCaseDatabase,
                                     FreesortExecutionTrace,
                                     FreesortProcedureDashboardModel,
                                     FreesortProcedureSettingsModel,
                                     FreesortMsgAcceptManualModel,
                                     FreesortProcedureBackend<STUDENT_MODEL>,
                                     FreesortProcedureRootView,
                                     FreesortCardsView,
                                     FreesortProcedureDashboardView,
                                     FreesortProcedureSettingsView,
                                     FreesortProcedureMessagesView,
                                     FreesortProcedureMessageFragment,
                                     FreesortProcedureViewData,
                                     FreesortProcedureFrontend<STUDENT_MODEL>> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@Override
	protected Class<TriLibre> executableModelClass() {
		return TriLibre.class;
	}


	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<FreesortTestCase> testCaseClass() {
		return FreesortTestCase.class;
	}

	@Override
	protected Class<FreesortTestCaseDatabase> testCasesModelClass() {
		return FreesortTestCaseDatabase.class;
	}


	@Override
	protected Class<FreesortProcedureDashboardModel> dashboardModelClass() {
		return FreesortProcedureDashboardModel.class;
	}


	@Override
	protected Class<FreesortProcedureSettingsModel> settingsModelClass() {
		return FreesortProcedureSettingsModel.class;
	}

	@Override
	protected FreesortProcedureFrontend createFrontend() {
		return new FreesortProcedureFrontend();
	}


	@Override
	protected FreesortProcedureBackend createBackend() {
		return new FreesortProcedureBackend();
	}


	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class<STUDENT_MODEL> canvasModelClass() {
		return (Class<STUDENT_MODEL>) classeTriNaif();
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}

	@Override
	protected Class<FreesortTestCaseDescriptor> testCaseDescriptorClass() {
		return FreesortTestCaseDescriptor.class;
	}

	@Override
	protected Class<FreesortMsgAcceptManualModel> msgAcceptManualModelClass() {
		return FreesortMsgAcceptManualModel.class;
	}

	@Override
	protected String initialTestCaseId() {
		return "ex01";
	}

}
