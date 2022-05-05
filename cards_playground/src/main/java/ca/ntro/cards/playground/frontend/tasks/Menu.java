package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.cards.playground.frontend.views.PlaygroundMenuView;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.NtroApp;

public class Menu {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("Menu")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 quit(subTasks);
		    	 
		    	 displayModel(subTasks);

		     });
	}

	private static void quit(FrontendTasks tasks) {
		tasks.task("quit")
		
		     .waitsFor(event(EvtQuit.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 NtroApp.exit(() -> {
		    		 
		    		 System.out.println("Quitting");
		    		 
		    	 });

		     });
	}

	private static void displayModel(FrontendTasks tasks) {
		tasks.task("displaySettingsModel")
		
		     .waitsFor(modified(PlaygroundSettingsModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundMenuView                menuView      = inputs.get(created(PlaygroundMenuView.class));
		    	 Modified<PlaygroundSettingsModel> settingsModel = inputs.get(modified(PlaygroundSettingsModel.class));
		    	 
		    	 settingsModel.currentValue().displayOn(menuView);

		     });
	}
}
