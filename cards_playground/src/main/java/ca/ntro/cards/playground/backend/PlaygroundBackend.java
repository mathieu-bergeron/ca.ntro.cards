package ca.ntro.cards.playground.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.playground.models.PlaygroundCardsModel;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;

public class PlaygroundBackend extends CommonBackend<PlaygroundCardsModel,
                                                     PlaygroundSettingsModel> {

	@Override
	protected Class<PlaygroundCardsModel> cardsModelClass() {
		return PlaygroundCardsModel.class;
	}

	@Override
	protected Class<PlaygroundSettingsModel> settingsModelClass() {
		return PlaygroundSettingsModel.class;
	}

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {
	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
	}


}
