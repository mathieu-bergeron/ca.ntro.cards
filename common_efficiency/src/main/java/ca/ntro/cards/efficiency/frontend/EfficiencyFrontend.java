package ca.ntro.cards.efficiency.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyGraphsView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyMessagesView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyDashboardView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyRootView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.cards.efficiency.frontend.views.fragments.EfficiencyMessageFragment;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public abstract class EfficiencyFrontend<ROOT_VIEW       extends EfficiencyRootView, 
                                         SETTINGS_VIEW    extends EfficiencySettingsView,
                                         CANVAS_VIEW      extends EfficiencyGraphsView, 
                                         DASHBOARD_VIEW   extends EfficiencyDashboardView,
                                         MESSAGES_VIEW    extends EfficiencyMessagesView,
                                         MESSAGE_FRAGMENT extends EfficiencyMessageFragment,
                                         VIEW_DATA        extends EfficiencyViewData,
                                         GRAPHS_MODEL     extends EfficiencyGraphsModel,
                                         DASHBOARD_MODEL  extends EfficiencyDashboardModel,
                                         SETTINGS_MODEL   extends EfficiencySettingsModel>

       extends CommonFrontend<ROOT_VIEW, 
                              SETTINGS_VIEW, 
                              CANVAS_VIEW, 
                              DASHBOARD_VIEW,
                              MESSAGES_VIEW,
                              MESSAGE_FRAGMENT,
                              VIEW_DATA,
                              GRAPHS_MODEL,
                              DASHBOARD_MODEL,
                              SETTINGS_MODEL> {

	@Override
	protected void addDashboardSubViewLoaders(FrontendTasks subTasks) {
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
	protected void addSubTasksToMessages(FrontendTasks subTasks) {

	}

	@Override
	protected void createAdditionnalTasks(FrontendTasks tasks) {
		
	}

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {

	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected void installDashboardSubViews(SimpleTaskCreator<?> taskCreator) {
		taskCreator.thenExecutes(inputs -> {});
	}

}
