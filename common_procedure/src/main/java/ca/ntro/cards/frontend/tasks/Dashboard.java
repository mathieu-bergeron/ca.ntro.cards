package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.messages.MsgExecutionEnded;
import ca.ntro.cards.models.ExploreCardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Modified;
import common.frontend.events.EvtMoveViewport;
import common.frontend.events.EvtResizeViewport;
import common.frontend.events.MouseEvtOnTabletop;
import common.frontend.events.MouseEvtOnViewer;
import common.frontend.views.CardsView;
import common.frontend.views.DashboardView;
import common.frontend.views.data.CardsViewData;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class Dashboard {

	public static <DASHBOARD_VIEW extends DashboardView,
	               DASHBOARD_MODEL extends DashboardModel>

	       void createTasks(FrontendTasks tasks,
	    		            Class<DASHBOARD_VIEW> dashboardViewClass,
	    		            Class<DASHBOARD_MODEL> dashboardModelClass,
	    		            SubTasksLambda<FrontendTasks> subTasksLambda) {

		tasks.taskGroup("Dashboard")

		     .waitsFor("Initialization")

		     .andContains(subTasks -> {

		    	 displayDashboardModel(subTasks,
		    			              dashboardViewClass,
		    			              dashboardModelClass);

		    	 executionEnded(subTasks,
		    			        dashboardViewClass);

		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}

	private static <DASHBOARD_VIEW extends DashboardView,
	                DASHBOARD_MODEL extends DashboardModel> 
	
	        void displayDashboardModel(FrontendTasks tasks, 
			                          Class<DASHBOARD_VIEW> dashboardViewClass, 
			                          Class<DASHBOARD_MODEL> dashboardModelClass) {

		tasks.task("displayDashboardModel")
		
		     .waitsFor(modified(dashboardModelClass))
		     
		     .thenExecutes(inputs -> {
		    	 
		    	 DashboardView             dashboardView  = inputs.get(created(dashboardViewClass));
		    	 Modified<DASHBOARD_MODEL> dashboardModel = inputs.get(modified(dashboardModelClass));
		    	 
		    	 dashboardModel.currentValue().displayOn(dashboardView);
		     });
	}

	private static <DASHBOARD_VIEW extends DashboardView,
	                DASHBOARD_MODEL extends DashboardModel> 
	
	        void executionEnded(FrontendTasks tasks, 
			                    Class<DASHBOARD_VIEW> dashboardViewClass) {

		tasks.task("executionEndedDashboard")
		
			 .waitsFor(message(MsgExecutionEnded.class))
		
		     .thenExecutes(inputs -> {
		    	 
		    	 DashboardView dashboardView  = inputs.get(created(dashboardViewClass));
		    	 
		    	 //dashboardView.disableExecutionButtons();

		     });
	}

}
