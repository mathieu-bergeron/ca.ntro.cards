package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundMainView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.services.Window;

public class InitialWindow {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("InitialWindow")

		     .contains(subTasks -> {
		    	 
		    	 createRootView(subTasks);
		    	 createMainView(subTasks);
		    	 createDashboardView(subTasks);
		    	 
		    	 installRootView(subTasks);
		    	 installMainView(subTasks);
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

	private static void createMainView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundMainView.class))
		
		     .waitsFor(viewLoader(PlaygroundMainView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundMainView> mainViewLoader = inputs.get(viewLoader(PlaygroundMainView.class));
		    	 
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

	private static void installMainView(FrontendTasks tasks) {
		tasks.task("installMainView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(PlaygroundMainView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView rootView = inputs.get(created(PlaygroundRootView.class));
		    	 PlaygroundMainView mainView = inputs.get(created(PlaygroundMainView.class));
		    	 
		    	 rootView.displaySubView(mainView);
		     });
	}

	private static void installDashboardView(FrontendTasks tasks) {
		tasks.task("installDashboardView")
		
		     .waitsFor("installMainView")

		     .waitsFor(created(PlaygroundDashboardView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundMainView      mainView      = inputs.get(created(PlaygroundMainView.class));
		    	 PlaygroundDashboardView dashboardView = inputs.get(created(PlaygroundDashboardView.class));
		    	 
		    	 mainView.displayDashboardView(dashboardView);
		     });
	}

	private static void showWindow(FrontendTasks tasks) {
		tasks.task("showWindow")
		
		     .waitsFor("installTabletopView")
		     
		     .thenExecutes(inputs -> {

		    	 Window window = inputs.get(window());
		    	 
		    	 window.show();
		     });
	}

}
