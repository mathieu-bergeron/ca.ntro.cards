package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.demo.messages.MsgFlipCard;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoModel;

public class DemoRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerModels(registrar);

		registrar.registerModel(DemoModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgUpdateList.class);
		registrar.registerMessage(MsgFlipCard.class);
	}
}
