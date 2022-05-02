package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundGameView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.services.Window;

public class InitialWindow {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("InitialWindow")

		     .contains(subTasks -> {
		    	 
		    	 createRootView(subTasks);
		    	 createGameView(subTasks);
		    	 createDashboardView(subTasks);
		    	 
		    	 installRootView(subTasks);
		    	 installGameView(subTasks);
		    	 installDashboardView(subTasks);
		    	 
		    	 showWindow(subTasks);

		     });
	}

	private static void createRootView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundRootView.class))
		
		     .waitsFor(viewLoader(PlaygroundRootView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundRootView> rootViewLoader = inputs.get(viewLoader(PlaygroundRootView.class));

		    	 return rootViewLoader.createView();
		     });
	}

	private static void createGameView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundGameView.class))
		
		     .waitsFor(viewLoader(PlaygroundGameView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundGameView> mainViewLoader = inputs.get(viewLoader(PlaygroundGameView.class));
		    	 
		    	 return mainViewLoader.createView();
		     });
		
	}

	private static void createDashboardView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundDashboardView.class))
		
		     .waitsFor(viewLoader(PlaygroundDashboardView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundDashboardView> dashboardViewLoader = inputs.get(viewLoader(PlaygroundDashboardView.class));
		    	 
		    	 return dashboardViewLoader.createView();
		     });
		
	}

	private static void installRootView(FrontendTasks tasks) {
		tasks.task("installRootView")

		     .waitsFor(window())
		
		     .waitsFor(created(PlaygroundRootView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));
		    	 Window             window   = inputs.get(window());
		    	 
		    	 window.installRootView(rootView);
		     });
	}

	private static void installGameView(FrontendTasks tasks) {
		tasks.task("installGameView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(PlaygroundGameView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));
		    	 PlaygroundGameView mainView = inputs.get(created(PlaygroundGameView.class));
		    	 
		    	 rootView.displaySubView(mainView);
		     });
	}

	private static void installDashboardView(FrontendTasks tasks) {
		tasks.task("installDashboardView")
		
		     .waitsFor("installGameView")

		     .waitsFor(created(PlaygroundDashboardView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundGameView      mainView      = inputs.get(created(PlaygroundGameView.class));
		    	 PlaygroundDashboardView dashboardView = inputs.get(created(PlaygroundDashboardView.class));
		    	 
		    	 mainView.displayDashboardView(dashboardView);
		     });
	}

	private static void showWindow(FrontendTasks tasks) {
		tasks.task("showWindow")
		
		     .waitsFor("installDashboardView")
		     
		     .thenExecutes(inputs -> {

		    	 Window window = inputs.get(window());
		    	 
		    	 window.show();
		     });
	}

}
