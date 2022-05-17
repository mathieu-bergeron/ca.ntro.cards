package ca.ntro.cards.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.events.EvtMoveViewport;
import ca.ntro.cards.frontend.events.EvtResizeViewport;
import ca.ntro.cards.frontend.events.MouseEvtOnTabletop;
import ca.ntro.cards.frontend.events.MouseEvtOnViewer;
import ca.ntro.cards.frontend.views.CardsView;
import ca.ntro.cards.frontend.views.DashboardView;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;
import ca.ntro.core.clock.Tick;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Modified;

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

}
