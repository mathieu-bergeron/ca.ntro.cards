package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.demo.backend.DemoProcedureBackend;
import ca.ntro.cards.demo.frontend.DemoProcedureFrontend;
import ca.ntro.cards.demo.frontend.DemoProcedureViewData;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureRootView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureSettingsView;
import ca.ntro.cards.demo.messages.MsgManualExecutionStep;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.demo.test_cases.execution_trace.DemoExecutionTrace;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;

public abstract class   DemoProcedureApp<STUDENT_MODEL extends DemoCardsModel>

                extends ProcedureApp<DemoCardsModel,           // executable model
                                     STUDENT_MODEL,
                                     STUDENT_MODEL,     // canvas model
                                     DemoTestCase,
                                     DemoTestCaseDatabase,
                                     DemoExecutionTrace,
                                     DemoProcedureDashboardModel,
                                     DemoProcedureSettingsModel,
                                     DemoProcedureBackend<STUDENT_MODEL>,
                                     DemoProcedureRootView,
                                     DemoCardsView,
                                     DemoProcedureDashboardView,
                                     DemoProcedureSettingsView,
                                     DemoProcedureViewData,
                                     DemoProcedureFrontend<STUDENT_MODEL>> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@Override
	protected Class<DemoCardsModel> executableModelClass() {
		return DemoCardsModel.class;
	}


	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<DemoTestCase> testCaseClass() {
		return DemoTestCase.class;
	}

	@Override
	protected Class<DemoTestCaseDatabase> testCasesModelClass() {
		return DemoTestCaseDatabase.class;
	}


	@Override
	protected Class<DemoProcedureDashboardModel> dashboardModelClass() {
		return DemoProcedureDashboardModel.class;
	}


	@Override
	protected Class<DemoProcedureSettingsModel> settingsModelClass() {
		return DemoProcedureSettingsModel.class;
	}

	@Override
	protected DemoProcedureFrontend createFrontend() {
		return new DemoProcedureFrontend();
	}


	@Override
	protected DemoProcedureBackend createBackend() {
		return new DemoProcedureBackend();
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


}
