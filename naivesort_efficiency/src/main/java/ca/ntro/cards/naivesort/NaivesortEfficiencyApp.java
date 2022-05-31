package ca.ntro.cards.naivesort;


import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.naivesort.backend.NaivesortEfficiencyBackend;
import ca.ntro.cards.naivesort.frontend.NaivesortEfficiencyFrontend;
import ca.ntro.cards.naivesort.frontend.NaivesortEfficiencyViewData;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencyDashboardView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencyRootView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencySettingsView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortGraphsView;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencyDashboardModel;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencySettingsModel;
import ca.ntro.cards.naivesort.models.NaivesortGraphsModel;
import ca.ntro.cards.naivesort.models.NaivesortCardsModel;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class   NaivesortEfficiencyApp<STUDENT_MODEL extends NaivesortCardsModel>

                extends EfficiencyApp<NaivesortCardsModel, 
                                      STUDENT_MODEL,
                                      NaivesortGraphsModel,
                                      NaivesortTestCase,
                                      NaivesortTestCaseDatabase,
                                      NaivesortExecutionTrace,
                                      NaivesortEfficiencyDashboardModel,
                                      NaivesortEfficiencySettingsModel,
                                      NaivesortEfficiencyBackend<STUDENT_MODEL>,
                                      NaivesortEfficiencyRootView,
                                      NaivesortGraphsView,
                                      NaivesortEfficiencyDashboardView,
                                      NaivesortEfficiencySettingsView,
                                      NaivesortEfficiencyViewData,
                                      NaivesortEfficiencyFrontend> {

	@Override
	protected NaivesortEfficiencyFrontend createFrontend() {
		return new NaivesortEfficiencyFrontend();
	}

	@Override
	protected NaivesortEfficiencyBackend createBackend() {
		return new NaivesortEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<NaivesortCardsModel> executableModelClass() {
		return NaivesortCardsModel.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}
	
	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<NaivesortGraphsModel> canvasModelClass() {
		return NaivesortGraphsModel.class;
	}

	@Override
	protected Class<NaivesortTestCase> testCaseClass() {
		return NaivesortTestCase.class;
	}

	@Override
	protected Class<NaivesortTestCaseDatabase> testCasesModelClass() {
		return NaivesortTestCaseDatabase.class;
	}

	@Override
	protected Class<NaivesortEfficiencyDashboardModel> dashboardModelClass() {
		return NaivesortEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<NaivesortEfficiencySettingsModel> settingsModelClass() {
		return NaivesortEfficiencySettingsModel.class;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		
	}
}
