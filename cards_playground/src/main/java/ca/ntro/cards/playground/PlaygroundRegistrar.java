package ca.ntro.cards.playground;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.playground.messages.MsgUpdateList;
import ca.ntro.cards.playground.models.PlaygroundModel;

public class PlaygroundRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerModels(registrar);

		registrar.registerModel(PlaygroundModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgUpdateList.class);
	}
}
