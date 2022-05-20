package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.SubTasksLambda;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.views.CanvasView;
import ca.ntro.cards.common.frontend.views.DashboardView;
import ca.ntro.cards.common.frontend.views.RootView;
import ca.ntro.cards.common.frontend.views.SettingsView;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.services.Window;

public class Initialization {

	public static <ROOT_VIEW extends RootView, 
	               CARDS_VIEW extends CanvasView,
	               SETTINGS_VIEW extends SettingsView,
	               DASHBOARD_VIEW extends DashboardView> 
	
	void createTasks(FrontendTasks tasks,
			         Class<ROOT_VIEW> rootViewClass,
			         Class<CARDS_VIEW> cardsViewClass,
			         Class<SETTINGS_VIEW> settingsViewClass,
			         Class<DASHBOARD_VIEW> dashboardViewClass,
			         SubTasksLambda<FrontendTasks> subTaskLambda) {

		tasks.taskGroup("Initialization")

		     .contains(subTasks -> {
		    	 
		    	 createRootView(subTasks,
		    			        rootViewClass);

		    	 createCardsView(subTasks,
		    			         cardsViewClass);

		    	 createSettingsView(subTasks,
		    			            settingsViewClass);

		    	 createDashboardView(subTasks,
		    			             dashboardViewClass);
		    	 
		    	 installRootView(subTasks,
		    			         rootViewClass);

		    	 registerCardsView(subTasks,
		    			           rootViewClass,
		    			           cardsViewClass);

		    	 registerSettingsView(subTasks,
		    			              rootViewClass,
		    			              settingsViewClass);
		    	 
		    	 installSubViews(subTasks,
		    			         rootViewClass);
		    	 
		    	 installDashboardView(subTasks,
		    			              cardsViewClass,
		    			              dashboardViewClass);

		    	 showWindow(subTasks);
		    	 
		    	 subTaskLambda.createSubTasks(subTasks);

		     });
	}

	private static <ROOT_VIEW extends RootView> void createRootView(FrontendTasks tasks,
			                                                        Class<ROOT_VIEW> rootViewClass) {

		tasks.task(create(rootViewClass))
		
		     .waitsFor(viewLoader(rootViewClass))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<ROOT_VIEW> rootViewLoader = inputs.get(viewLoader(rootViewClass));

		    	 return rootViewLoader.createView();
		     });
	}

	private static <CARDS_VIEW extends CanvasView> void createCardsView(FrontendTasks tasks,
			                                                           Class<CARDS_VIEW> cardsViewClass) {

		tasks.task(create(cardsViewClass))
		
		     .waitsFor(viewLoader(cardsViewClass))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<CARDS_VIEW> cardsViewLoader = inputs.get(viewLoader(cardsViewClass));
		    	 
		    	 return cardsViewLoader.createView();
		     });
		
	}

	private static <SETTINGS_VIEW extends SettingsView> void createSettingsView(FrontendTasks tasks,
			                                                                    Class<SETTINGS_VIEW> settingsViewClass) {

		tasks.task(create(settingsViewClass))
		
		     .waitsFor(viewLoader(settingsViewClass))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<SETTINGS_VIEW> settingsViewLoader = inputs.get(viewLoader(settingsViewClass));
		    	 
		    	 return settingsViewLoader.createView();
		     });
		
	}

	private static <DASHBOARD_VIEW extends DashboardView> void createDashboardView(FrontendTasks tasks,
			                                                                       Class<DASHBOARD_VIEW> dashboardViewClass) {

		tasks.task(create(dashboardViewClass))
		
		     .waitsFor(viewLoader(dashboardViewClass))
		     
		     .thenExecutesAndReturnsValue(inputs -> {
		    	 
		    	 ViewLoader<DASHBOARD_VIEW> dashboardViewLoader = inputs.get(viewLoader(dashboardViewClass));
		    	 
		    	 return dashboardViewLoader.createView();
		     });
		
	}

	private static <ROOT_VIEW extends RootView> void installRootView(FrontendTasks tasks,
			                                                         Class<ROOT_VIEW> rootViewClass) {

		tasks.task("installRootView")

		     .waitsFor(window())
		
		     .waitsFor(created(rootViewClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));
		    	 Window    window   = inputs.get(window());
		    	 
		    	 window.installRootView(rootView);
		     });
	}

	private static <ROOT_VIEW extends RootView,
	                CARDS_VIEW extends CanvasView> void registerCardsView(FrontendTasks tasks,
	                		                                             Class<ROOT_VIEW> rootViewClass,
	                		                                             Class<CARDS_VIEW> cardsViewClass) {

		tasks.task("registerCardsView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(cardsViewClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 ROOT_VIEW  rootView   = inputs.get(created(rootViewClass));
		    	 CARDS_VIEW cardsView = inputs.get(created(cardsViewClass));
		    	 
		    	 rootView.registerCardsView(cardsView);
		     });
	}

	private static <ROOT_VIEW extends RootView,
	                SETTINGS_VIEW extends SettingsView> 

	        void registerSettingsView(FrontendTasks tasks,
	        		                  Class<ROOT_VIEW> rootViewClass,
	        		                  Class<SETTINGS_VIEW> settingsViewClass) {

		tasks.task("registerSettingsView")
		
		     .waitsFor("installRootView")

		     .waitsFor(created(settingsViewClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 ROOT_VIEW     rootView     = inputs.get(created(rootViewClass));
		    	 SETTINGS_VIEW settingsView = inputs.get(created(settingsViewClass));

		    	 rootView.registerSettingsView(settingsView);
		     });
	}

	private static <ROOT_VIEW extends RootView> void installSubViews(FrontendTasks tasks, 
			                                                         Class<ROOT_VIEW> rootViewClass) {

		tasks.task("installSubViews")

		     .waitsFor("registerCardsView")
		
		     .waitsFor("registerSettingsView")

		     .thenExecutes(inputs -> {
		    	 
		    	 ROOT_VIEW rootView = inputs.get(created(rootViewClass));

		    	 rootView.installSubViews();
		     });
	}

	private static <CARDS_VIEW extends CanvasView,
	                DASHBOARD_VIEW extends DashboardView> 
	
	        void installDashboardView(FrontendTasks tasks,
	        		                  Class<CARDS_VIEW> cardsViewClass,
	        		                  Class<DASHBOARD_VIEW> dashboardViewClass) {

		tasks.task("installDashboardView")
		
		     .waitsFor("registerCardsView")

		     .waitsFor(created(dashboardViewClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 CARDS_VIEW     cardsView     = inputs.get(created(cardsViewClass));
		    	 DASHBOARD_VIEW dashboardView = inputs.get(created(dashboardViewClass));
		    	 
		    	 cardsView.displayDashboardView(dashboardView);
		     });
	}

	private static void showWindow(FrontendTasks tasks) {
		tasks.task("showWindow")
		
		     .waitsFor("installSubViews")

		     .waitsFor("installDashboardView")
		     
		     .thenExecutes(inputs -> {

		    	 Window window = inputs.get(window());
		    	 
		    	 window.resize(1080, 640);
		    	 
		    	 window.show();
		     });
	}

}
