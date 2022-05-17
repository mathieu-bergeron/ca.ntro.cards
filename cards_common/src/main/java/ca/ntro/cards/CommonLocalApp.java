package ca.ntro.cards;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
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
                                                                                                      DASHBOARD_MODEL>> 

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

	protected abstract Class<CARDS_MODEL> cardsModelClass();
	protected abstract Class<DASHBOARD_MODEL> dashboardModelClass();
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();
	protected abstract Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass();

	protected abstract void registerAdditionnalModels(ModelRegistrar registrar);
	protected abstract void registerAdditionnalMessages(MessageRegistrar registrar);

}
