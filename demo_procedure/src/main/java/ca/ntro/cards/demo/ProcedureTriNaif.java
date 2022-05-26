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
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;

public abstract class   ProcedureTriNaif<STUDENT_MODEL extends TriNaif>

                extends ProcedureApp<TriNaif,           // executable model
                                     STUDENT_MODEL,
                                     TriNaif,           // canvas model
                                     DemoTestCase,
                                     DemoTestCaseDatabase,
                                     DemoProcedureDashboardModel,
                                     DemoProcedureSettingsModel,
                                     DemoProcedureBackend<STUDENT_MODEL>,
                                     DemoProcedureRootView,
                                     DemoCardsView,
                                     DemoProcedureDashboardView,
                                     DemoProcedureSettingsView,
                                     DemoProcedureViewData,
                                     DemoProcedureFrontend> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@Override
	protected Class<TriNaif> executableModelClass() {
		return TriNaif.class;
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
		registrar.registerMessage(MsgUpdateList.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class<TriNaif> canvasModelClass() {
		return (Class<TriNaif>) classeTriNaif();
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}


}
