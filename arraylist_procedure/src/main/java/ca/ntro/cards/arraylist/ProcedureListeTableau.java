package ca.ntro.cards.arraylist;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.arraylist.backend.ArraylistProcedureBackend;
import ca.ntro.cards.arraylist.frontend.ArraylistProcedureFrontend;
import ca.ntro.cards.arraylist.frontend.ArraylistProcedureViewData;
import ca.ntro.cards.arraylist.frontend.views.ArraylistCardsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureMessagesView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureDashboardView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureRootView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureSettingsView;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistProcedureMessageFragment;
import ca.ntro.cards.arraylist.messages.ArraylistMsgAcceptManualModel;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.descriptor.ArraylistTestCaseDescriptor;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureSettingsModel;

public abstract class   ProcedureListeTableau<STUDENT_MODEL extends ListeTableau>

                extends ProcedureApp<ListeTableau,           // executable model
                                     STUDENT_MODEL,
                                     STUDENT_MODEL,     // canvas model
                                     ArraylistTestCase,
                                     ArraylistTestCaseDescriptor,
                                     ArraylistTestCaseDatabase,
                                     ArraylistExecutionTrace,
                                     ArraylistProcedureDashboardModel,
                                     ArraylistProcedureSettingsModel,
                                     ArraylistMsgAcceptManualModel,
                                     ArraylistProcedureBackend<STUDENT_MODEL>,
                                     ArraylistProcedureRootView,
                                     ArraylistCardsView,
                                     ArraylistProcedureDashboardView,
                                     ArraylistProcedureSettingsView,
                                     ArraylistProcedureMessagesView,
                                     ArraylistProcedureMessageFragment,
                                     ArraylistProcedureViewData,
                                     ArraylistProcedureFrontend<STUDENT_MODEL>> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@Override
	protected Class<ListeTableau> executableModelClass() {
		return ListeTableau.class;
	}

	protected abstract Class<STUDENT_MODEL> classListeTableau();

	@Override
	protected Class<ArraylistTestCase> testCaseClass() {
		return ArraylistTestCase.class;
	}

	@Override
	protected Class<ArraylistTestCaseDatabase> testCasesModelClass() {
		return ArraylistTestCaseDatabase.class;
	}


	@Override
	protected Class<ArraylistProcedureDashboardModel> dashboardModelClass() {
		return ArraylistProcedureDashboardModel.class;
	}


	@Override
	protected Class<ArraylistProcedureSettingsModel> settingsModelClass() {
		return ArraylistProcedureSettingsModel.class;
	}

	@Override
	protected ArraylistProcedureFrontend createFrontend() {
		return new ArraylistProcedureFrontend();
	}


	@Override
	protected ArraylistProcedureBackend createBackend() {
		return new ArraylistProcedureBackend();
	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class<STUDENT_MODEL> canvasModelClass() {
		return (Class<STUDENT_MODEL>) classListeTableau();
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classListeTableau();
	}

	@Override
	protected Class<ArraylistTestCaseDescriptor> testCaseDescriptorClass() {
		return ArraylistTestCaseDescriptor.class;
	}

	@Override
	protected Class<ArraylistMsgAcceptManualModel> msgAcceptManualModelClass() {
		return ArraylistMsgAcceptManualModel.class;
	}


}
