package ca.ntro.cards.common.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.common.messages.MsgSpeedUp;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.CommonConstants;

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
		    	 
		    	 initializeUseTwoTimeSpeedToggleButton(subTasks, settingsModelClass);
		    	 
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
	
	private static <SETTINGS_MODEL extends CommonSettingsModel> 

    void initializeUseTwoTimeSpeedToggleButton(BackendTasks tasks,
            Class<SETTINGS_MODEL> settingsModelClass) {
		
		tasks.task("initializeUseTwoTimeSpeedToggleButton")
		
			.waitsFor(message(MsgSpeedUp.class))
			
			.thenExecutes(inputs ->{
				double oldSpeed=CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS;
				CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS=(int)(oldSpeed/2);
			});
			
	}
	
}
