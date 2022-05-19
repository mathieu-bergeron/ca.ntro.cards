package ca.ntro.cards;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.frontend.CommonFrontend;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.RootView;
import ca.ntro.cards.frontend.views.SettingsView;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.messages.MsgGenerateTestCase;
import ca.ntro.cards.messages.MsgRefreshDashboard;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.messages.MsgLockThread;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.messages.MsgExecutionEnded;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.cards.models.TestCasesModel;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.values.TestCase;
import ca.ntro.cards.models.values.TestCaseById;
import ca.ntro.cards.models.values.TestCasesByCategory;
import ca.ntro.cards.models.values.TestCasesBySize;

public abstract class CommonApp<CARDS_MODEL extends CardsModel,
                                TEST_CASE extends TestCase<CARDS_MODEL>,
                                TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                DASHBOARD_MODEL extends DashboardModel,
                                SETTINGS_MODEL extends SettingsModel,
                                                                                                      
                                BACKEND extends CommonBackend<CARDS_MODEL, 
                                                              TEST_CASE, 
                                                              TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL>,
                                   
                                ROOT_VIEW extends RootView, 
                                CARDS_VIEW extends CardsView, 
                                DASHBOARD_VIEW extends DashboardView,
                                SETTINGS_VIEW extends SettingsView,
                                CARDS_VIEW_DATA extends CardsViewData,
                                     
                               FRONTEND extends CommonFrontend<ROOT_VIEW, 
                                                               SETTINGS_VIEW, 
                                                               CARDS_VIEW, 
                                                               DASHBOARD_VIEW, 
                                                               CARDS_VIEW_DATA,
                                                               CARDS_MODEL,
                                                               DASHBOARD_MODEL,
                                                               SETTINGS_MODEL>>

       implements NtroClientFx {
	

	@Override
	public void registerModels(ModelRegistrar registrar) {
		registrar.registerModel(cardsModelClass());
		registrar.registerValue(Card.class);

		registrar.registerModel(dashboardModelClass());
		registrar.registerModel(settingsModelClass());

		registrar.registerModel(testCasesModelClass());
		registrar.registerValue(testCaseClass());

		registrar.registerValue(TestCaseById.class);
		registrar.registerValue(TestCasesByCategory.class);
		registrar.registerValue(TestCasesBySize.class);

		registerAdditionnalModels(registrar);
	}


	@Override
	public void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgFlipCard.class);
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
		registrar.registerMessage(MsgLockThread.class);
		registrar.registerMessage(MsgExecutionEnded.class);
		registrar.registerMessage(MsgExecutionStepBack.class);
		registrar.registerMessage(MsgExecutionStepForward.class);
		registrar.registerMessage(MsgRefreshDashboard.class);
		registrar.registerMessage(MsgGenerateTestCase.class);

		registerAdditionnalMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		FRONTEND frontend = createFrontend();
		
		frontend.setCardsModelClass(cardsModelClass());
		frontend.setDashboardModelClass(dashboardModelClass());
		frontend.setSettingsModelClass(settingsModelClass());
		
		registrar.registerFrontend(frontend);

	}



	@Override
	public void registerBackend(BackendRegistrar registrar) {
		BACKEND backend = createBackend();
		
		backend.setCardsModelClass(cardsModelClass());
		backend.setTestCaseClass(testCaseClass());
		backend.setTestCasesModelClass(testCasesModelClass());
		backend.setDashboardModelClass(dashboardModelClass());
		backend.setSettingsModelClass(settingsModelClass());

		registrar.registerBackend(backend);

	}

	protected abstract Class<CARDS_MODEL> cardsModelClass();
	protected abstract Class<TEST_CASE> testCaseClass();
	protected abstract Class<TEST_CASES_MODEL> testCasesModelClass();
	protected abstract Class<DASHBOARD_MODEL> dashboardModelClass();
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();

	protected abstract void registerAdditionnalModels(ModelRegistrar registrar);
	protected abstract void registerAdditionnalMessages(MessageRegistrar registrar);

	protected abstract FRONTEND createFrontend();
	protected abstract BACKEND createBackend();


}
