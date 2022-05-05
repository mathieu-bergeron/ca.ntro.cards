package ca.ntro.cards.playground.backend.tasks;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;

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

		     .waitsFor(model(PlaygroundSettingsModel.class))

		     .waitsFor(message(MsgToggleUseFourCardColors.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundSettingsModel settingsModel = inputs.get(model(PlaygroundSettingsModel.class));

		    	 settingsModel.toggleUseFourCardColors();

		     });
	}
}
