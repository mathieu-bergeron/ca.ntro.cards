package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundGameView;
import ca.ntro.cards.playground.frontend.views.PlaygroundMenuView;

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
		    	 createMenuView(subTasks);
		    	 
		    	 installRootView(subTasks);
		    	 registerGameView(subTasks);
		    	 registerMenuView(subTasks);
		    	 
		    	 installSubViews(subTasks);
		    	 
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
		    	 
		    	 ViewLoader<PlaygroundGameView> gameViewLoader = inputs.get(viewLoader(PlaygroundGameView.class));
		    	 
		    	 return gameViewLoader.createView();
		     });
		
	}

	private static void createMenuView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundMenuView.class))
		
		     .waitsFor(viewLoader(PlaygroundMenuView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundMenuView> menuViewLoader = inputs.get(viewLoader(PlaygroundMenuView.class));
		    	 
		    	 return menuViewLoader.createView();
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

	private static void registerGameView(FrontendTasks tasks) {
		tasks.task("registerGameView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(PlaygroundGameView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));
		    	 PlaygroundGameView gameView = inputs.get(created(PlaygroundGameView.class));
		    	 
		    	 rootView.registerGameView(gameView);
		     });
	}

	private static void registerMenuView(FrontendTasks tasks) {
		tasks.task("registerMenuView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(PlaygroundMenuView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));
		    	 PlaygroundMenuView menuView = inputs.get(created(PlaygroundMenuView.class));
		    	 
		    	 rootView.registerMenuView(menuView);
		     });
	}

	private static void installSubViews(FrontendTasks tasks) {
		tasks.task("installSubViews")

		     .waitsFor("registerGameView")
		
		     .waitsFor("registerMenuView")

		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));

		    	 rootView.installSubViews();
		     });
	}

	private static void installDashboardView(FrontendTasks tasks) {
		tasks.task("installDashboardView")
		
		     .waitsFor("registerGameView")

		     .waitsFor(created(PlaygroundDashboardView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundGameView      gameView      = inputs.get(created(PlaygroundGameView.class));
		    	 PlaygroundDashboardView dashboardView = inputs.get(created(PlaygroundDashboardView.class));
		    	 
		    	 gameView.displayDashboardView(dashboardView);
		     });
	}

	private static void showWindow(FrontendTasks tasks) {
		tasks.task("showWindow")
		
		     .waitsFor("installSubViews")

		     .waitsFor("installDashboardView")
		     
		     .thenExecutes(inputs -> {

		    	 Window window = inputs.get(window());
		    	 
		    	 window.show();
		     });
	}

}
