package ca.ntro.cards.demo.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.models.SettingsModel;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

public class ModifySettingsModel {

	public static void createTasks(BackendTasks tasks) {
		
		tasks.taskGroup("ModifySettingsModel")
		
		     .contains(subTasks -> {

		    	 toggleUseFourCardColors(subTasks);


		     });
	}

	private static void toggleUseFourCardColors(BackendTasks tasks) {
		tasks.task("toggleUseFourCardColors")

		     .waitsFor(model(DemoSettingsModel.class))

		     .waitsFor(message(MsgToggleUseFourCardColors.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 SettingsModel settingsModel = inputs.get(model(DemoSettingsModel.class));

		    	 settingsModel.toggleUseFourCardColors();

		     });
	}
}
