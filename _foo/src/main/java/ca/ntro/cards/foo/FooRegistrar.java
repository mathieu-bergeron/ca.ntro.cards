package ca.ntro.cards.foo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.foo.messages.MsgUpdateList;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooSettingsModel;

public class FooRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerValues(registrar);

		registrar.registerModel(FooCardsModel.class);
		registrar.registerModel(FooSettingsModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		CommonRegistrar.registerMessages(registrar);

		registrar.registerMessage(MsgUpdateList.class);
	}
}
