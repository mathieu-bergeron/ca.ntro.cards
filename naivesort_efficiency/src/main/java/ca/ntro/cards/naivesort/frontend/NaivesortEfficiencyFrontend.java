package ca.ntro.cards.naivesort.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencyDashboardView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencyRootView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortEfficiencySettingsView;
import ca.ntro.cards.naivesort.frontend.views.NaivesortGraphsView;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencyDashboardModel;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencySettingsModel;
import ca.ntro.cards.naivesort.models.NaivesortGraphsModel;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class NaivesortEfficiencyFrontend 

       extends EfficiencyFrontend<NaivesortEfficiencyRootView,
                                  NaivesortEfficiencySettingsView, 
                                  NaivesortGraphsView, 
                                  NaivesortEfficiencyDashboardView, 
                                  NaivesortEfficiencyViewData, 
                                  NaivesortGraphsModel, 
                                  NaivesortEfficiencyDashboardModel, 
                                  NaivesortEfficiencySettingsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<NaivesortEfficiencyRootView> rootViewClass() {
		return NaivesortEfficiencyRootView.class;
	}

	@Override
	protected Class<NaivesortEfficiencySettingsView> settingsViewClass() {
		return NaivesortEfficiencySettingsView.class;
	}

	@Override
	protected Class<NaivesortGraphsView> canvasViewClass() {
		return NaivesortGraphsView.class;
	}

	@Override
	protected Class<NaivesortEfficiencyDashboardView> dashboardViewClass() {
		return NaivesortEfficiencyDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<NaivesortEfficiencyViewData> viewDataClass() {
		return NaivesortEfficiencyViewData.class;
	}

	@Override
	protected void addSubTasksToInitialization(FrontendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToViewData(FrontendTasks subTasks) {
		
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
