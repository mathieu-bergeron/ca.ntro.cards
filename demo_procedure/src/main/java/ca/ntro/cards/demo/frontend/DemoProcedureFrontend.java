package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.demo.frontend.views.DemoProcedureRootView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureSettingsView;
import ca.ntro.cards.demo.frontend.views.DemoReplayView;
import ca.ntro.cards.demo.frontend.views.DemoVariablesView;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoSelectionsView;
import ca.ntro.cards.demo.frontend.views.DemoProcedureDashboardView;

public class DemoProcedureFrontend<STUDENT_MODEL extends DemoCardsModel>

       extends ProcedureFrontend<DemoProcedureRootView,
                                DemoProcedureSettingsView, 
                                DemoCardsView, 
                                DemoProcedureDashboardView, 
                                DemoSelectionsView,
                                DemoReplayView,
                                DemoVariablesView,
                                DemoProcedureViewData, 
                                STUDENT_MODEL, // CanvasModel
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
	protected void addSubTasksToNavigation(FrontendTasks subTasks) {

	}

	@Override
	protected void addSubTasksToSettings(FrontendTasks subTasks) {

	}


	@Override
	protected Class<DemoSelectionsView> selectionsViewClass() {
		return DemoSelectionsView.class;
	}

	@Override
	protected Class<DemoReplayView> replayControlsViewClass() {
		return DemoReplayView.class;
	}

	@Override
	protected Class<DemoVariablesView> variablesViewClass() {
		return DemoVariablesView.class;
	}

	@Override
	protected void addSubTasksToCards(FrontendTasks subTasks) {
	}



}
