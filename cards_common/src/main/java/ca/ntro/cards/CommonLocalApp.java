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
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.cards.models.values.Card;

public abstract class CommonLocalApp<CARDS_MODEL extends CardsModel,
                                     DASHBOARD_MODEL extends DashboardModel,
                                     SETTINGS_MODEL extends SettingsModel,
                                     MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, 
                                                                                                      DASHBOARD_MODEL>,
                                                                                                      
                                     BACKEND extends CommonBackend<CARDS_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL, MSG_REGISTER_SIMPLE_OPERATION>,
                                     
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
                                                                     SETTINGS_MODEL,
                                                                     MSG_REGISTER_SIMPLE_OPERATION>> 

       implements NtroClientFx {
	

	@Override
	public void registerModels(ModelRegistrar registrar) {
		registrar.registerModel(cardsModelClass());
		registrar.registerModel(dashboardModelClass());
		registrar.registerModel(settingsModelClass());
		
		registrar.registerValue(Card.class);
		
		registerAdditionnalModels(registrar);
	}


	@Override
	public void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgFlipCard.class);
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
		registrar.registerMessage(msgRegisterSimpleOperationClass());
		
		registerAdditionnalMessages(registrar);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		FRONTEND frontend = createFrontend();
		
		frontend.setCardsModelClass(cardsModelClass());
		frontend.setDashboardModelClass(dashboardModelClass());
		frontend.setSettingsModelClass(settingsModelClass());
		frontend.setMsgRegisterSimpleOperationClass(msgRegisterSimpleOperationClass());
		
		registrar.registerFrontend(frontend);

	}



	@Override
	public void registerBackend(BackendRegistrar registrar) {
		BACKEND backend = createBackend();
		
		backend.setCardsModelClass(cardsModelClass());
		backend.setDashboardModelClass(dashboardModelClass());
		backend.setSettingsModelClass(settingsModelClass());
		backend.setMsgRegisterSimpleOperationClass(msgRegisterSimpleOperationClass());

		registrar.registerBackend(backend);

	}

	protected abstract Class<CARDS_MODEL> cardsModelClass();
	protected abstract Class<DASHBOARD_MODEL> dashboardModelClass();
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();
	protected abstract Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass();

	protected abstract void registerAdditionnalModels(ModelRegistrar registrar);
	protected abstract void registerAdditionnalMessages(MessageRegistrar registrar);

	protected abstract FRONTEND createFrontend();
	protected abstract BACKEND createBackend();

}
