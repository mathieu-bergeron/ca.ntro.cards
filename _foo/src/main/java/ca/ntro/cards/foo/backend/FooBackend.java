package ca.ntro.cards.foo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.foo.backend.tasks.FooModifyCardsModel;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooSettingsModel;

public class FooBackend extends CommonBackend<FooCardsModel,
                                               FooSettingsModel> {

	@Override
	protected Class<FooCardsModel> cardsModelClass() {
		return FooCardsModel.class;
	}

	@Override
	protected Class<FooSettingsModel> settingsModelClass() {
		return FooSettingsModel.class;
	}

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 FooModifyCardsModel.updateList(subTasks);

	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
		
	}

}
