package ca.ntro.cards;

import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.models.values.Card;

public class CommonRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		registrar.registerValue(Card.class);
	}

}
