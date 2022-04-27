package ca.ntro.cards.playground.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundTabletopView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.services.Window;

public class InitialWindow {

	public static void createTasks(FrontendTasks tasks) {
		tasks.taskGroup("InitialWindow")

		     .contains(subTasks -> {
		    	 
		    	 createRootView(subTasks);
		    	 createTabletopView(subTasks);
		    	 
		    	 installRootView(subTasks);
		    	 installTabletopView(subTasks);
		    	 
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

	private static void createTabletopView(FrontendTasks tasks) {
		tasks.task(create(PlaygroundTabletopView.class))
		
		     .waitsFor(viewLoader(PlaygroundTabletopView.class))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<PlaygroundTabletopView> tabletopViewLoader = inputs.get(viewLoader(PlaygroundTabletopView.class));
		    	 
		    	 return tabletopViewLoader.createView();
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

	private static void installTabletopView(FrontendTasks tasks) {
		tasks.task("installTabletopView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(PlaygroundTabletopView.class))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 PlaygroundRootView     rootView     = inputs.get(created(PlaygroundRootView.class));
		    	 PlaygroundTabletopView tabletopView = inputs.get(created(PlaygroundTabletopView.class));
		    	 
		    	 rootView.displaySubView(tabletopView);
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
