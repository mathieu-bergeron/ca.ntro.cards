package ca.ntro.cards.common.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.events.EvtHideMenu;
import ca.ntro.cards.common.frontend.events.EvtHideMessages;
import ca.ntro.cards.common.frontend.events.EvtShowMenu;
import ca.ntro.cards.common.frontend.events.EvtShowMessages;
import ca.ntro.cards.common.frontend.views.CommonRootView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class Navigation {

	public static <ROOT_VIEW extends CommonRootView> void createTasks(FrontendTasks tasks,
			                                                    Class<ROOT_VIEW> rootViewClass,
			                                                    SubTasksLambda<FrontendTasks> subTasksLambda) {

		tasks.taskGroup("Navigation")
		
		     .waitsFor("Initialization")

		     .andContains(subTasks -> {
		    	 
		    	 showMenu(subTasks, rootViewClass);

		    	 hideMenu(subTasks, rootViewClass);

		    	 showMessages(subTasks, rootViewClass);

		    	 hideMessages(subTasks, rootViewClass);
		    	 
		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static <ROOT_VIEW extends CommonRootView> void showMenu(FrontendTasks tasks,
			                                                  Class<ROOT_VIEW> rootViewClass) {
		tasks.task("showMenu")
		
		     .waitsFor(event(EvtShowMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.showMenu();

		     });
	}

	private static <ROOT_VIEW extends CommonRootView> void showMessages(FrontendTasks tasks,
			                                                            Class<ROOT_VIEW> rootViewClass) {
		tasks.task("showMessages")
		
		     .waitsFor(event(EvtShowMessages.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.showMessages();

		     });
	}


	private static <ROOT_VIEW extends CommonRootView> void hideMenu(FrontendTasks tasks,
			                                                  Class<ROOT_VIEW> rootViewClass) {
		tasks.task("hideMenu")
		
		     .waitsFor(event(EvtHideMenu.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.hideMenu();

		     });
	}

	private static <ROOT_VIEW extends CommonRootView> void hideMessages(FrontendTasks tasks,
			                                                  Class<ROOT_VIEW> rootViewClass) {
		tasks.task("hideMessages")
		
		     .waitsFor(event(EvtHideMessages.class))
		     
		     .thenExecutes(inputs -> {

		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 
		    	 rootView.hideMessages();

		     });
	}
}
