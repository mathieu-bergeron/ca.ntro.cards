package ca.ntro.cards.naivesort;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.naivesort.backend.NaivesortProcedureBackend;
import ca.ntro.cards.naivesort.frontend.NaivesortProcedureFrontend;
import ca.ntro.cards.naivesort.frontend.NaivesortProcedureViewData;
import ca.ntro.cards.naivesort.frontend.views.NaivesortCardsView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortProcedureDashboardView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortProcedureRootView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortProcedureSettingsView;
import ca.ntro.cards.naivesort.messages.MsgManualExecutionStep;
import ca.ntro.cards.naivesort.models.NaivesortCardsModel;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.descriptor.NaivesortTestCaseDescriptor;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.naivesort.models.NaivesortProcedureSettingsModel;

public abstract class   NaivesortProcedureApp<STUDENT_MODEL extends NaivesortCardsModel>

                extends ProcedureApp<NaivesortCardsModel,           // executable model
                                     STUDENT_MODEL,
                                     STUDENT_MODEL,     // canvas model
                                     NaivesortTestCase,
                                     NaivesortTestCaseDescriptor,
                                     NaivesortTestCaseDatabase,
                                     NaivesortExecutionTrace,
                                     NaivesortProcedureDashboardModel,
                                     NaivesortProcedureSettingsModel,
                                     NaivesortProcedureBackend<STUDENT_MODEL>,
                                     NaivesortProcedureRootView,
                                     NaivesortCardsView,
                                     NaivesortProcedureDashboardView,
                                     NaivesortProcedureSettingsView,
                                     NaivesortProcedureViewData,
                                     NaivesortProcedureFrontend<STUDENT_MODEL>> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@Override
	protected Class<NaivesortCardsModel> executableModelClass() {
		return NaivesortCardsModel.class;
	}


	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<NaivesortTestCase> testCaseClass() {
		return NaivesortTestCase.class;
	}

	@Override
	protected Class<NaivesortTestCaseDatabase> testCasesModelClass() {
		return NaivesortTestCaseDatabase.class;
	}


	@Override
	protected Class<NaivesortProcedureDashboardModel> dashboardModelClass() {
		return NaivesortProcedureDashboardModel.class;
	}


	@Override
	protected Class<NaivesortProcedureSettingsModel> settingsModelClass() {
		return NaivesortProcedureSettingsModel.class;
	}

	@Override
	protected NaivesortProcedureFrontend createFrontend() {
		return new NaivesortProcedureFrontend();
	}


	@Override
	protected NaivesortProcedureBackend createBackend() {
		return new NaivesortProcedureBackend();
	}


	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgManualExecutionStep.class);
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
	protected Class<NaivesortTestCaseDescriptor> testCaseDescriptorClass() {
		return NaivesortTestCaseDescriptor.class;
	}


}
