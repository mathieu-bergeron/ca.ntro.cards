package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;

import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.common.models.CommonSettingsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class ModifySettingsModel {

	public static <SETTINGS_MODEL extends CommonSettingsModel> 

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

	private static <SETTINGS_MODEL extends CommonSettingsModel> 

	        void toggleUseFourCardColors(BackendTasks tasks,
	        		                     Class<SETTINGS_MODEL> settingsModelClass) {

		tasks.task("toggleUseFourCardColors")

		     .waitsFor(message(MsgToggleUseFourCardColors.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CommonSettingsModel settingsModel = inputs.get(model(settingsModelClass));

		    	 settingsModel.toggleUseFourCardColors();

		     });
	}
}
