package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.SubTasksLambda;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.cards.frontend.views.SettingsView;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.NtroApp;

public class Settings {

	public static <SETTINGS_VIEW extends SettingsView,
	               SETTINGS_MODEL extends SettingsModel> 
	
	       void createTasks(FrontendTasks tasks,
	    		            Class<SETTINGS_VIEW> settingsViewClass,
	    		            Class<SETTINGS_MODEL> settingsModelClass,
	    		            SubTasksLambda<FrontendTasks> subTasksLambda) {

		tasks.taskGroup("Menu")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 quit(subTasks);

		    	 displaySettingsModel(subTasks,
		    			              settingsViewClass,
		    			              settingsModelClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static void quit(FrontendTasks tasks) {
		tasks.task("quit")
		
		     .waitsFor(event(EvtQuit.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 NtroApp.exit(() -> {
		    		 
		    	 });

		     });
	}

	private static <SETTINGS_VIEW extends SettingsView,
	                SETTINGS_MODEL extends SettingsModel> 
	
	void displaySettingsModel(FrontendTasks tasks,
			                  Class<SETTINGS_VIEW> settingsViewClass,
			                  Class<SETTINGS_MODEL> settingsModelClass) {

		tasks.task("displaySettingsModel")
		
		     .waitsFor(modified(settingsModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 SettingsView             settingsView  = inputs.get(created(settingsViewClass));
		    	 Modified<SETTINGS_MODEL> settingsModel = inputs.get(modified(settingsModelClass));
		    	 
		    	 settingsModel.currentValue().displayOn(settingsView);

		     });
	}
}
