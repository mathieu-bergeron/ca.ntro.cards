package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import common.frontend.events.EvtHideMenu;
import common.frontend.events.EvtShowMenu;
import common.frontend.views.RootView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class Navigation {

	public static <ROOT_VIEW extends RootView> void createTasks(FrontendTasks tasks,
			                                                    Class<ROOT_VIEW> rootViewClass,
			                                                    SubTasksLambda<FrontendTasks> subTasksLambda) {

		tasks.taskGroup("Navigation")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 showMenu(subTasks, rootViewClass);

		    	 hideMenu(subTasks, rootViewClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

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
