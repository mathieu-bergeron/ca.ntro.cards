package ca.ntro.cards.foo;


import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.foo.backend.FooEfficiencyBackend;
import ca.ntro.cards.foo.frontend.FooEfficiencyFrontend;
import ca.ntro.cards.foo.frontend.FooEfficiencyViewData;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyDashboardView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyRootView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencySettingsView;
import ca.ntro.cards.foo.frontend.views.FooGraphsView;
import ca.ntro.cards.foo.models.FooEfficiencyDashboardModel;
import ca.ntro.cards.foo.models.FooEfficiencySettingsModel;
import ca.ntro.cards.foo.models.FooGraphsModel;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.values.FooTestCase;
import ca.ntro.cards.foo.test_cases.FooTestCaseDatabase;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class   FooEfficiencyApp<STUDENT_MODEL extends FooCardsModel>

                extends EfficiencyApp<FooCardsModel, 
                                      STUDENT_MODEL,
                                      FooGraphsModel,
                                      FooTestCase,
                                      FooTestCaseDatabase,
                                      FooExecutionTrace,
                                      FooEfficiencyDashboardModel,
                                      FooEfficiencySettingsModel,
                                      FooEfficiencyBackend<STUDENT_MODEL>,
                                      FooEfficiencyRootView,
                                      FooGraphsView,
                                      FooEfficiencyDashboardView,
                                      FooEfficiencySettingsView,
                                      FooEfficiencyViewData,
                                      FooEfficiencyFrontend> {

	@Override
	protected FooEfficiencyFrontend createFrontend() {
		return new FooEfficiencyFrontend();
	}

	@Override
	protected FooEfficiencyBackend createBackend() {
		return new FooEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<FooCardsModel> executableModelClass() {
		return FooCardsModel.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}
	
	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<FooGraphsModel> canvasModelClass() {
		return FooGraphsModel.class;
	}

	@Override
	protected Class<FooTestCase> testCaseClass() {
		return FooTestCase.class;
	}

	@Override
	protected Class<FooTestCaseDatabase> testCasesModelClass() {
		return FooTestCaseDatabase.class;
	}

	@Override
	protected Class<FooEfficiencyDashboardModel> dashboardModelClass() {
		return FooEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<FooEfficiencySettingsModel> settingsModelClass() {
		return FooEfficiencySettingsModel.class;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		
	}
}
