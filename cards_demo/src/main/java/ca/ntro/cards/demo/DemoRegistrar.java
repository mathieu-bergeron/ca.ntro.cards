package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoGameModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;

public class DemoRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerValues(registrar);

		registrar.registerModel(DemoGameModel.class);
		registrar.registerModel(DemoSettingsModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		CommonRegistrar.registerMessages(registrar);

		registrar.registerMessage(MsgUpdateList.class);
	}
}
