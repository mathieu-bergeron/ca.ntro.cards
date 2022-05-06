package ca.ntro.cards.freesort.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.freesort.backend.tasks.FreesortModifyCardsModel;
import ca.ntro.cards.freesort.models.FreesortCardsModel;
import ca.ntro.cards.freesort.models.FreesortSettingsModel;

public class FreesortBackend extends CommonBackend<FreesortCardsModel,
                                               FreesortSettingsModel> {

	@Override
	protected Class<FreesortCardsModel> cardsModelClass() {
		return FreesortCardsModel.class;
	}

	@Override
	protected Class<FreesortSettingsModel> settingsModelClass() {
		return FreesortSettingsModel.class;
	}

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 FreesortModifyCardsModel.updateList(subTasks);

	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
		
	}

}
