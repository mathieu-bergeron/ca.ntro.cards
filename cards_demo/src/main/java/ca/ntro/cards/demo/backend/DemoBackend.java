package ca.ntro.cards.demo.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.CommonBackend;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;

public class DemoBackend extends CommonBackend<DemoCardsModel,
                                               DemoSettingsModel> {

	@Override
	protected Class<DemoCardsModel> cardsModelClass() {
		return DemoCardsModel.class;
	}

	@Override
	protected Class<DemoSettingsModel> settingsModelClass() {
		return DemoSettingsModel.class;
	}

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 DemoModifyCardsModel.updateList(subTasks);

	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
		
	}

	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		
	}

}
