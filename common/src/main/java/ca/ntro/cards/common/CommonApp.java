package ca.ntro.cards.common;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.frontend.views.CommonRootView;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.messages.MsgGenerateTestCase;
import ca.ntro.cards.common.messages.MsgLockThread;
import ca.ntro.cards.common.messages.MsgRefreshDashboard;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceFull;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTraceSizeOnly;
import ca.ntro.cards.common.test_cases.indexing.TestCaseById;
import ca.ntro.cards.common.test_cases.indexing.TestCasesByCategory;
import ca.ntro.cards.common.test_cases.indexing.TestCasesBySize;

public abstract class CommonApp<EXECUTABLE_MODEL extends CommonExecutableModel,
                                STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                CANVAS_MODEL     extends CommonCanvasModel,
                                TEST_CASE        extends TestCase,
                                TEST_CASES_MODEL extends TestCasesModel,
                                DASHBOARD_MODEL  extends CommonDashboardModel,
                                SETTINGS_MODEL   extends CommonSettingsModel,
                                                                                                      
                                BACKEND extends CommonBackend<EXECUTABLE_MODEL, 
                                                              STUDENT_MODEL,
                                                              CANVAS_MODEL,
                                                              TEST_CASE, 
                                                              TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL>,
                                   
                                ROOT_VIEW       extends CommonRootView, 
                                CARDS_VIEW      extends CommonCanvasView, 
                                DASHBOARD_VIEW  extends CommonDashboardView,
                                SETTINGS_VIEW   extends CommonSettingsView,
                                VIEW_DATA       extends CommonViewData,
                                     
                                FRONTEND extends CommonFrontend<ROOT_VIEW, 
                                                                SETTINGS_VIEW, 
                                                                CARDS_VIEW, 
                                                                DASHBOARD_VIEW, 
                                                                VIEW_DATA,
                                                                CANVAS_MODEL,
                                                                DASHBOARD_MODEL,
                                                                SETTINGS_MODEL>>

                implements NtroClientFx {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		registrar.registerModel(executableModelClass());
		registrar.registerModel(studentModelClass());
		registrar.registerModel(canvasModelClass());

		registrar.registerModel(dashboardModelClass());
		registrar.registerModel(settingsModelClass());

		registrar.registerModel(testCasesModelClass());
		registrar.registerValue(testCaseClass());

		registrar.registerValue(Card.class);
		registrar.registerValue(AbstractCard.class);

		registrar.registerValue(TestCaseById.class);
		registrar.registerValue(TestCasesByCategory.class);
		registrar.registerValue(TestCasesBySize.class);

		registrar.registerValue(ExecutionTraceFull.class);
		registrar.registerValue(ExecutionTraceSizeOnly.class);

		registerAdditionnalModels(registrar);
	}


	@Override
	public void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgExecutionEnded.class);
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
		registrar.registerMessage(MsgLockThread.class);
		registrar.registerMessage(MsgRefreshDashboard.class);
		registrar.registerMessage(MsgGenerateTestCase.class);

		registerAdditionnalMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		FRONTEND frontend = createFrontend();
		
		frontend.setCanvasModelClass(canvasModelClass());
		frontend.setDashboardModelClass(dashboardModelClass());
		frontend.setSettingsModelClass(settingsModelClass());
		
		registrar.registerFrontend(frontend);
	}



	@Override
	public void registerBackend(BackendRegistrar registrar) {
		BACKEND backend = createBackend();
		
		backend.setExecutableModelClass(executableModelClass());
		backend.setStudentModelClass(studentModelClass());
		backend.setCanvasModelClass(canvasModelClass());
		backend.setTestCaseClass(testCaseClass());
		backend.setTestCasesModelClass(testCasesModelClass());
		backend.setDashboardModelClass(dashboardModelClass());
		backend.setSettingsModelClass(settingsModelClass());
		
		backend.initialize();

		registrar.registerBackend(backend);

	}

	protected abstract Class<EXECUTABLE_MODEL> executableModelClass();
	protected abstract Class<STUDENT_MODEL> studentModelClass();
	protected abstract Class<CANVAS_MODEL> canvasModelClass();
	protected abstract Class<TEST_CASE> testCaseClass();
	protected abstract Class<TEST_CASES_MODEL> testCasesModelClass();
	protected abstract Class<DASHBOARD_MODEL> dashboardModelClass();
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();

	protected abstract void registerAdditionnalModels(ModelRegistrar registrar);
	protected abstract void registerAdditionnalMessages(MessageRegistrar registrar);

	protected abstract FRONTEND createFrontend();
	protected abstract BACKEND createBackend();

}
