package ca.ntro.cards.arraylist;

import ca.ntro.cards.arraylist.backend.ArraylistEfficiencyBackend;
import ca.ntro.cards.arraylist.frontend.ArraylistEfficiencyFrontend;
import ca.ntro.cards.arraylist.frontend.ArraylistEfficiencyViewData;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyDashboardView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyMessagesView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyRootView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencySettingsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistGraphsView;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistEfficiencyMessageFragment;
import ca.ntro.cards.arraylist.models.ArraylistEfficiencyDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistEfficiencySettingsModel;
import ca.ntro.cards.arraylist.models.ArraylistGraphsModel;
import ca.ntro.cards.arraylist.models.ArraylistCardsModel;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.efficiency.EfficiencyApp;

public abstract class   ArraylistEfficiencyApp<STUDENT_MODEL extends ArraylistCardsModel>

                extends EfficiencyApp<ArraylistCardsModel, 
                                      STUDENT_MODEL,
                                      ArraylistGraphsModel,
                                      ArraylistTestCase,
                                      ArraylistTestCaseDatabase,
                                      ArraylistExecutionTrace,
                                      ArraylistEfficiencyDashboardModel,
                                      ArraylistEfficiencySettingsModel,
                                      ArraylistEfficiencyBackend<STUDENT_MODEL>,
                                      ArraylistEfficiencyRootView,
                                      ArraylistGraphsView,
                                      ArraylistEfficiencyDashboardView,
                                      ArraylistEfficiencySettingsView,
                                      ArraylistEfficiencyMessagesView,
                                      ArraylistEfficiencyMessageFragment,
                                      ArraylistEfficiencyViewData,
                                      ArraylistEfficiencyFrontend> {

	@Override
	protected ArraylistEfficiencyFrontend createFrontend() {
		return new ArraylistEfficiencyFrontend();
	}

	@Override
	protected ArraylistEfficiencyBackend createBackend() {
		return new ArraylistEfficiencyBackend();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<ArraylistCardsModel> executableModelClass() {
		return ArraylistCardsModel.class;
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return classeTriNaif();
	}
	
	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<ArraylistGraphsModel> canvasModelClass() {
		return ArraylistGraphsModel.class;
	}

	@Override
	protected Class<ArraylistTestCase> testCaseClass() {
		return ArraylistTestCase.class;
	}

	@Override
	protected Class<ArraylistTestCaseDatabase> testCasesModelClass() {
		return ArraylistTestCaseDatabase.class;
	}

	@Override
	protected Class<ArraylistEfficiencyDashboardModel> dashboardModelClass() {
		return ArraylistEfficiencyDashboardModel.class;
	}

	@Override
	protected Class<ArraylistEfficiencySettingsModel> settingsModelClass() {
		return ArraylistEfficiencySettingsModel.class;
	}
}
