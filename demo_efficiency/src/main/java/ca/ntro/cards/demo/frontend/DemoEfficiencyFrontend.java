package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyRootView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencySettingsView;
import ca.ntro.cards.demo.frontend.views.DemoGraphsView;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class DemoEfficiencyFrontend 

       extends EfficiencyFrontend<DemoEfficiencyRootView,
                                  DemoEfficiencySettingsView, 
                                  DemoGraphsView, 
                                  DemoEfficiencyDashboardView, 
                                  DemoEfficiencyViewData, 
                                  DemoGraphsModel, 
                                  DemoEfficiencyDashboardModel, 
                                  DemoEfficiencySettingsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isProd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Class<DemoEfficiencyRootView> rootViewClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DemoEfficiencySettingsView> settingsViewClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DemoGraphsView> canvasViewClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DemoEfficiencyDashboardView> dashboardViewClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<DemoEfficiencyViewData> viewDataClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addSubTasksToInitialization(FrontendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToCards(FrontendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToNavigation(FrontendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToSettings(FrontendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addSubTasksToDashboard(FrontendTasks subTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createAdditionnalTasks(FrontendTasks tasks) {
		// TODO Auto-generated method stub
		
	}

}
