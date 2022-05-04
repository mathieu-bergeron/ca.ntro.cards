package ca.ntro.cards.demo.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoGameView;
import ca.ntro.cards.demo.frontend.views.DemoMenuView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.services.Window;

public class Initialization {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("Initialization")

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
		tasks.task(create(DemoRootView.class))
		
		     .waitsFor(viewLoader(DemoRootView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<DemoRootView> rootViewLoader = inputs.get(viewLoader(DemoRootView.class));

		    	 return rootViewLoader.createView();
		     });
	}

	private static void createGameView(FrontendTasks tasks) {
		tasks.task(create(DemoGameView.class))
		
		     .waitsFor(viewLoader(DemoGameView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<DemoGameView> gameViewLoader = inputs.get(viewLoader(DemoGameView.class));
		    	 
		    	 return gameViewLoader.createView();
		     });
		
	}

	private static void createMenuView(FrontendTasks tasks) {
		tasks.task(create(DemoMenuView.class))
		
		     .waitsFor(viewLoader(DemoMenuView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<DemoMenuView> menuViewLoader = inputs.get(viewLoader(DemoMenuView.class));
		    	 
		    	 return menuViewLoader.createView();
		     });
		
	}

	private static void createDashboardView(FrontendTasks tasks) {
		tasks.task(create(DemoDashboardView.class))
		
		     .waitsFor(viewLoader(DemoDashboardView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<DemoDashboardView> dashboardViewLoader = inputs.get(viewLoader(DemoDashboardView.class));
		    	 
		    	 return dashboardViewLoader.createView();
		     });
		
	}

	private static void installRootView(FrontendTasks tasks) {
		tasks.task("installRootView")

		     .waitsFor(window())
		
		     .waitsFor(created(DemoRootView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));
		    	 Window             window   = inputs.get(window());
		    	 
		    	 window.installRootView(rootView);
		     });
	}

	private static void registerGameView(FrontendTasks tasks) {
		tasks.task("registerGameView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(DemoGameView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));
		    	 DemoGameView gameView = inputs.get(created(DemoGameView.class));
		    	 
		    	 rootView.registerGameView(gameView);
		     });
	}

	private static void registerMenuView(FrontendTasks tasks) {
		tasks.task("registerMenuView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(DemoMenuView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));
		    	 DemoMenuView menuView = inputs.get(created(DemoMenuView.class));
		    	 
		    	 rootView.registerMenuView(menuView);
		     });
	}

	private static void installSubViews(FrontendTasks tasks) {
		tasks.task("installSubViews")

		     .waitsFor("registerGameView")
		
		     .waitsFor("registerMenuView")

		     .thenExecutes(inputs -> {
		    	 
		    	 DemoRootView rootView = inputs.get(created(DemoRootView.class));

		    	 rootView.installSubViews();
		     });
	}

	private static void installDashboardView(FrontendTasks tasks) {
		tasks.task("installDashboardView")
		
		     .waitsFor("registerGameView")

		     .waitsFor(created(DemoDashboardView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DemoGameView      gameView      = inputs.get(created(DemoGameView.class));
		    	 DemoDashboardView dashboardView = inputs.get(created(DemoDashboardView.class));
		    	 
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
