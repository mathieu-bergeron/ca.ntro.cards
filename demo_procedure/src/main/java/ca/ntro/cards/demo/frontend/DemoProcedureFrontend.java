package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoProcedureRootView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureSettingsView;
import ca.ntro.cards.demo.frontend.views.DemoReplayControlsView;
import ca.ntro.cards.demo.frontend.views.DemoVariablesView;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoCategoriesView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureDashboardView;

public class DemoProcedureFrontend

       extends ProcedureFrontend<DemoProcedureRootView,
                                DemoProcedureSettingsView, 
                                DemoCardsView, 
                                DemoProcedureDashboardView, 
                                DemoCategoriesView,
                                DemoReplayControlsView,
                                DemoVariablesView,
                                DemoProcedureViewData, 
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
	protected Class<DemoProcedureRootView> rootViewClass() {
		return DemoProcedureRootView.class;
	}

	@Override
	protected Class<DemoProcedureSettingsView> settingsViewClass() {
		return DemoProcedureSettingsView.class;
	}

	@Override
	protected Class<DemoCardsView> canvasViewClass() {
		return DemoCardsView.class;
	}

	@Override
	protected Class<DemoProcedureDashboardView> dashboardViewClass() {
		return DemoProcedureDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<DemoProcedureViewData> viewDataClass() {
		return DemoProcedureViewData.class;
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

	@Override
	protected Class<DemoCategoriesView> categoriesViewClass() {
		return DemoCategoriesView.class;
	}

	@Override
	protected Class<DemoReplayControlsView> replayControlsViewClass() {
		return DemoReplayControlsView.class;
	}

	@Override
	protected Class<DemoVariablesView> variablesViewClass() {
		return DemoVariablesView.class;
	}



}
