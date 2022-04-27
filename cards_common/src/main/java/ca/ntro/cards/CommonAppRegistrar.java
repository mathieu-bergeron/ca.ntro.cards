package ca.ntro.cards;

import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.models.values.Card;

public class CommonAppRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		registrar.registerValue(Card.class);
	}

	public static void registerEvents(EventRegistrar registrar) {
		
	}

}
