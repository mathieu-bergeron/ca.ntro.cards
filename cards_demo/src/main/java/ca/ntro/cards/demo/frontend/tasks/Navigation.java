package ca.ntro.cards.demo.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtHideMenu;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import ca.ntro.cards.demo.frontend.views.DemoRootView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class Navigation {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("Navigation")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 showMenu(subTasks);
		    	 hideMenu(subTasks);

		     });
	}

	private static void showMenu(FrontendTasks tasks) {
		tasks.task("showMenu")
		
		     .waitsFor(event(EvtShowMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));
		    	 
		    	 rootView.showMenu();

		     });
	}

	private static void hideMenu(FrontendTasks tasks) {
		tasks.task("hideMenu")
		
		     .waitsFor(event(EvtHideMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));
		    	 
		    	 rootView.hideMenu();

		     });
	}
}
