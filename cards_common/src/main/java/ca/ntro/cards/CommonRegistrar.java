package ca.ntro.cards;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.models.values.Card;

public class CommonRegistrar {

	public static void registerValues(ModelRegistrar registrar) {
		registrar.registerValue(Card.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgFlipCard.class);
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
	}


}
