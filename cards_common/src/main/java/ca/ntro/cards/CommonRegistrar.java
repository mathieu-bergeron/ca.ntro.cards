package ca.ntro.cards;

import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.models.values.Card;

public class CommonRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		registrar.registerValue(Card.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
	}


}
