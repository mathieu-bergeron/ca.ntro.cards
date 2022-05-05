package ca.ntro.cards.playground;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;

public class PlaygroundRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerModels(registrar);
		
		registrar.registerModel(PlaygroundSettingsModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		CommonRegistrar.registerMessages(registrar);
	}
}
