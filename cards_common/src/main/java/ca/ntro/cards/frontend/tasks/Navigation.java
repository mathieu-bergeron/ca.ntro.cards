package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtHideMenu;
import ca.ntro.cards.frontend.events.EvtShowMenu;
import ca.ntro.cards.frontend.views.RootView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

public class Navigation {

	public static <ROOT_VIEW extends RootView> void createTasks(FrontendTasks tasks,
			                                                    Class<ROOT_VIEW> rootViewClass) {

		tasks.taskGroup("Navigation")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 showMenu(subTasks, rootViewClass);

		    	 hideMenu(subTasks, rootViewClass);

		     });
	}

	private static <ROOT_VIEW extends RootView> void showMenu(FrontendTasks tasks,
			                                                  Class<ROOT_VIEW> rootViewClass) {
		tasks.task("showMenu")
		
		     .waitsFor(event(EvtShowMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.showMenu();

		     });
	}

	private static <ROOT_VIEW extends RootView> void hideMenu(FrontendTasks tasks,
			                                                  Class<ROOT_VIEW> rootViewClass) {
		tasks.task("hideMenu")
		
		     .waitsFor(event(EvtHideMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.hideMenu();

		     });
	}
}
