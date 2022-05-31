package ca.ntro.cards.demo;


import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.demo.backend.DemoEfficiencyBackend;
import ca.ntro.cards.demo.frontend.DemoEfficiencyFrontend;
import ca.ntro.cards.demo.frontend.DemoEfficiencyViewData;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyRootView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencySettingsView;
import ca.ntro.cards.demo.frontend.views.DemoGraphsView;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.demo.test_cases.execution_trace.DemoExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class   DemoEfficiencyApp<STUDENT_MODEL extends DemoCardsModel>

                extends EfficiencyApp<DemoCardsModel, 
                                      STUDENT_MODEL,
                                      DemoGraphsModel,
                                      DemoTestCase,
                                      DemoTestCaseDatabase,
                                      DemoExecutionTrace,
                                      DemoEfficiencyDashboardModel,
                                      DemoEfficiencySettingsModel,
                                      DemoEfficiencyBackend<STUDENT_MODEL>,
                                      DemoEfficiencyRootView,
                                      DemoGraphsView,
                                      DemoEfficiencyDashboardView,
                                      DemoEfficiencySettingsView,
                                      DemoEfficiencyViewData,
                                      DemoEfficiencyFrontend> {

	@Override
	protected DemoEfficiencyFrontend createFrontend() {
		return new DemoEfficiencyFrontend();
	}

	@Override
	protected DemoEfficiencyBackend createBackend() {
		return new DemoEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<DemoCardsModel> executableModelClass() {
		return DemoCardsModel.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}
	
	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<DemoGraphsModel> canvasModelClass() {
		return DemoGraphsModel.class;
	}

	@Override
	protected Class<DemoTestCase> testCaseClass() {
		return DemoTestCase.class;
	}

	@Override
	protected Class<DemoTestCaseDatabase> testCasesModelClass() {
		return DemoTestCaseDatabase.class;
	}

	@Override
	protected Class<DemoEfficiencyDashboardModel> dashboardModelClass() {
		return DemoEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<DemoEfficiencySettingsModel> settingsModelClass() {
		return DemoEfficiencySettingsModel.class;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		
	}
}
