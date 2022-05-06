package ca.ntro.cards.freesort;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonRegistrar;
import ca.ntro.cards.freesort.messages.MsgUpdateList;
import ca.ntro.cards.freesort.models.FreesortCardsModel;
import ca.ntro.cards.freesort.models.FreesortSettingsModel;

public class FreesortRegistrar {

	public static void registerModels(ModelRegistrar registrar) {
		CommonRegistrar.registerValues(registrar);

		registrar.registerModel(FreesortCardsModel.class);
		registrar.registerModel(FreesortSettingsModel.class);
	}

	public static void registerMessages(MessageRegistrar registrar) {
		CommonRegistrar.registerMessages(registrar);

		registrar.registerMessage(MsgUpdateList.class);
	}
}
