package ca.ntro.cards.demo.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtQuit;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.NtroApp;

public class Menu {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("Menu")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 quit(subTasks);

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
}
