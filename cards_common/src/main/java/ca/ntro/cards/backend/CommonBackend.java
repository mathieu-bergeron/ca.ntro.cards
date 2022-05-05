package ca.ntro.cards.backend;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.tasks.ModifyCardsModel;
import ca.ntro.cards.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.SettingsModel;

public abstract class CommonBackend<CARDS_MODEL extends CardsModel,
                                    SETTINGS_MODEL extends SettingsModel> 

       extends LocalBackendNtro {

	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyCardsModel.createTasks(tasks, 
				                     cardsModelClass(),
				                     subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });

		ModifySettingsModel.createTasks(tasks,
				                        settingsModelClass(),
				                        subTasks -> {
				                        	
				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
	}
	

	protected abstract Class<CARDS_MODEL> cardsModelClass();
	protected abstract Class<SETTINGS_MODEL> settingsModelClass();

	protected abstract void addSubTasksToModifyCardsModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifySettingsModel(BackendTasks subTasks);


	@Override
	public void execute() {
		
	}

}
