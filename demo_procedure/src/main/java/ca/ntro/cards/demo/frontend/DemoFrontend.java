package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;

public class DemoFrontend

       extends ProcedureFrontend<DemoRootView,
                                DemoSettingsView, 
                                DemoCardsView, 
                                DemoDashboardView, 
                                DemoViewData, 
                                TriNaif, 
                                DemoProcedureDashboardModel, 
                                DemoProcedureSettingsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		
	}

	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<DemoRootView> rootViewClass() {
		return DemoRootView.class;
	}

	@Override
	protected Class<DemoSettingsView> settingsViewClass() {
		return DemoSettingsView.class;
	}

	@Override
	protected Class<DemoCardsView> canvasViewClass() {
		return DemoCardsView.class;
	}

	@Override
	protected Class<DemoDashboardView> dashboardViewClass() {
		return DemoDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<DemoViewData> viewDataClass() {
		return DemoViewData.class;
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