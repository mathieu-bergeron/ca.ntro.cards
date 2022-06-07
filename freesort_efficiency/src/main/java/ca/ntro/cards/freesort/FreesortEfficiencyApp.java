package ca.ntro.cards.freesort;


import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.freesort.backend.FreesortEfficiencyBackend;
import ca.ntro.cards.freesort.frontend.FreesortEfficiencyFrontend;
import ca.ntro.cards.freesort.frontend.FreesortEfficiencyViewData;
import ca.ntro.cards.freesort.frontend.views.FreesortEfficiencyDashboardView;
import ca.ntro.cards.freesort.frontend.views.FreesortEfficiencyMessagesView;
import ca.ntro.cards.freesort.frontend.views.FreesortEfficiencyRootView;
import ca.ntro.cards.freesort.frontend.views.FreesortEfficiencySettingsView;
import ca.ntro.cards.freesort.frontend.views.FreesortGraphsView;
import ca.ntro.cards.freesort.frontend.views.fragments.FreesortEfficiencyMessageFragment;
import ca.ntro.cards.freesort.models.FreesortEfficiencyDashboardModel;
import ca.ntro.cards.freesort.models.FreesortEfficiencySettingsModel;
import ca.ntro.cards.freesort.models.FreesortGraphsModel;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class   FreesortEfficiencyApp<STUDENT_MODEL extends TriLibre>

                extends EfficiencyApp<TriLibre, 
                                      STUDENT_MODEL,
                                      FreesortGraphsModel,
                                      FreesortTestCase,
                                      FreesortTestCaseDatabase,
                                      FreesortExecutionTrace,
                                      FreesortEfficiencyDashboardModel,
                                      FreesortEfficiencySettingsModel,
                                      FreesortEfficiencyBackend<STUDENT_MODEL>,
                                      FreesortEfficiencyRootView,
                                      FreesortGraphsView,
                                      FreesortEfficiencyDashboardView,
                                      FreesortEfficiencySettingsView,
                                      FreesortEfficiencyMessagesView,
                                      FreesortEfficiencyMessageFragment,
                                      FreesortEfficiencyViewData,
                                      FreesortEfficiencyFrontend> {

	@Override
	protected FreesortEfficiencyFrontend createFrontend() {
		return new FreesortEfficiencyFrontend();
	}

	@Override
	protected FreesortEfficiencyBackend createBackend() {
		return new FreesortEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<TriLibre> executableModelClass() {
		return TriLibre.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}
	
	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<FreesortGraphsModel> canvasModelClass() {
		return FreesortGraphsModel.class;
	}

	@Override
	protected Class<FreesortTestCase> testCaseClass() {
		return FreesortTestCase.class;
	}

	@Override
	protected Class<FreesortTestCaseDatabase> testCasesModelClass() {
		return FreesortTestCaseDatabase.class;
	}

	@Override
	protected Class<FreesortEfficiencyDashboardModel> dashboardModelClass() {
		return FreesortEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<FreesortEfficiencySettingsModel> settingsModelClass() {
		return FreesortEfficiencySettingsModel.class;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		
	}

	@Override
	protected String initialTestCaseId() {
		return "ex01";
	}
}
