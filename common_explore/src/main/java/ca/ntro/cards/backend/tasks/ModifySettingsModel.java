package ca.ntro.cards.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.models.SettingsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifySettingsModel {

	public static <SETTINGS_MODEL extends SettingsModel> 

	       void createTasks(BackendTasks tasks,
	    		            Class<SETTINGS_MODEL> settingsModelClass,
	    		            SubTasksLambda<BackendTasks> subTasksLambda) {
		
		tasks.taskGroup("ModifySettingsModel")
		
		     .waitsFor(model(settingsModelClass))
		
		     .andContains(subTasks -> {

		    	 toggleUseFourCardColors(subTasks, settingsModelClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static <SETTINGS_MODEL extends SettingsModel> 

	        void toggleUseFourCardColors(BackendTasks tasks,
	        		                     Class<SETTINGS_MODEL> settingsModelClass) {

		tasks.task("toggleUseFourCardColors")

		     .waitsFor(message(MsgToggleUseFourCardColors.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 SettingsModel settingsModel = inputs.get(model(settingsModelClass));

		    	 settingsModel.toggleUseFourCardColors();

		     });
	}
}
