package ca.ntro.cards.common.frontend.tasks;

import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.core.reflection.observer.Modified;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.tasks.SubTasksLambda;

public class Dashboard {

	public static <DASHBOARD_VIEW extends CommonDashboardView,
	               DASHBOARD_MODEL extends CommonDashboardModel>

	       void createTasks(FrontendTasks tasks,
	    		            Class<DASHBOARD_VIEW> dashboardViewClass,
	    		            Class<DASHBOARD_MODEL> dashboardModelClass,
	    		            SubTasksLambda<FrontendTasks> subTasksLambda) {

		tasks.taskGroup("Dashboard")

		     .waitsFor("Initialization")

		     .andContains(subTasks -> {

		    	 subTasksLambda.createSubTasks(subTasks);

		     });
	}
}
