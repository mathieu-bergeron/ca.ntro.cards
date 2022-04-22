package ca.ntro.cards.playground;

import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;

public class PlaygroundRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerModels(registrar);
	}

	public static void registerEvents(EventRegistrar registrar) {
		CommonRegistrar.registerEvents(registrar);
	}

}
