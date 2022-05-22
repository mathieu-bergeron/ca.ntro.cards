package ca.ntro.cards.efficiency.frontend;

import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyGraphsView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyDashboardView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyRootView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public abstract class EfficiencyFrontend<ROOT_VIEW       extends EfficiencyRootView, 
                                         SETTINGS_VIEW   extends EfficiencySettingsView,
                                         CANVAS_VIEW     extends EfficiencyGraphsView, 
                                         DASHBOARD_VIEW  extends EfficiencyDashboardView,
                                         VIEW_DATA       extends EfficiencyViewData,
                                         GRAPHS_MODEL    extends EfficiencyGraphsModel,
                                         DASHBOARD_MODEL extends EfficiencyDashboardModel,
                                         SETTINGS_MODEL  extends EfficiencySettingsModel>

       extends CommonFrontend<ROOT_VIEW, 
                              SETTINGS_VIEW, 
                              CANVAS_VIEW, 
                              DASHBOARD_VIEW,
                              VIEW_DATA,
                              GRAPHS_MODEL,
                              DASHBOARD_MODEL,
                              SETTINGS_MODEL> {

	@Override
	protected void addDashboardSubViewLoaders(FrontendTasks subTasks) {
	}

	@Override
	protected void installDashboardSubViews(SimpleTaskCreator<?> taskCreator) {
		taskCreator.thenExecutes(inputs -> {});
	}

}
