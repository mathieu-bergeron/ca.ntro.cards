package ca.ntro.cards.demo.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoMenuView;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.frontend.events.EvtQuit;
import ca.ntro.cards.frontend.views.MenuView;
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
		
		     .waitsFor(modified(DemoSettingsModel.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 MenuView                    menuView      = inputs.get(created(DemoMenuView.class));
		    	 Modified<DemoSettingsModel> settingsModel = inputs.get(modified(DemoSettingsModel.class));
		    	 
		    	 settingsModel.currentValue().displayOn(menuView);

		     });
	}
}
