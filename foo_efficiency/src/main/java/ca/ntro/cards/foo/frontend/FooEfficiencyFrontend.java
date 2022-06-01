package ca.ntro.cards.foo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyDashboardView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyMessagesView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyRootView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencySettingsView;
import ca.ntro.cards.foo.frontend.views.FooGraphsView;
import ca.ntro.cards.foo.frontend.views.fragments.FooEfficiencyMessageFragment;
import ca.ntro.cards.foo.models.FooEfficiencyDashboardModel;
import ca.ntro.cards.foo.models.FooEfficiencySettingsModel;
import ca.ntro.cards.foo.models.FooGraphsModel;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class FooEfficiencyFrontend 

       extends EfficiencyFrontend<FooEfficiencyRootView,
                                  FooEfficiencySettingsView, 
                                  FooGraphsView, 
                                  FooEfficiencyDashboardView, 
                                  FooEfficiencyMessagesView,
                                  FooEfficiencyMessageFragment,
                                  FooEfficiencyViewData, 
                                  FooGraphsModel, 
                                  FooEfficiencyDashboardModel, 
                                  FooEfficiencySettingsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<FooEfficiencyRootView> rootViewClass() {
		return FooEfficiencyRootView.class;
	}

	@Override
	protected Class<FooEfficiencySettingsView> settingsViewClass() {
		return FooEfficiencySettingsView.class;
	}

	@Override
	protected Class<FooGraphsView> canvasViewClass() {
		return FooGraphsView.class;
	}

	@Override
	protected Class<FooEfficiencyDashboardView> dashboardViewClass() {
		return FooEfficiencyDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<FooEfficiencyViewData> viewDataClass() {
		return FooEfficiencyViewData.class;
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

	@Override
	protected Class<FooEfficiencyMessagesView> messagesViewClass() {
		return FooEfficiencyMessagesView.class;
	}

	@Override
	protected Class<FooEfficiencyMessageFragment> messageFragmentClass() {
		return FooEfficiencyMessageFragment.class;
	}



}
