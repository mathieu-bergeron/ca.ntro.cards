package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
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
		return true;
	}

	@Override
	protected Class<DemoEfficiencyRootView> rootViewClass() {
		return DemoEfficiencyRootView.class;
	}

	@Override
	protected Class<DemoEfficiencySettingsView> settingsViewClass() {
		return DemoEfficiencySettingsView.class;
	}

	@Override
	protected Class<DemoGraphsView> canvasViewClass() {
		return DemoGraphsView.class;
	}

	@Override
	protected Class<DemoEfficiencyDashboardView> dashboardViewClass() {
		return DemoEfficiencyDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<DemoEfficiencyViewData> viewDataClass() {
		return DemoEfficiencyViewData.class;
	}

	@Override
	protected void addSubTasksToInitialization(FrontendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToCards(FrontendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToNavigation(FrontendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToSettings(FrontendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToDashboard(FrontendTasks subTasks) {
		
	}

	@Override
	protected void createAdditionnalTasks(FrontendTasks tasks) {
		
	}



}
