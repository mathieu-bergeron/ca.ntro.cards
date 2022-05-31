package ca.ntro.cards.foo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.foo.frontend.views.FooProcedureRootView;
import ca.ntro.cards.foo.frontend.views.FooProcedureSettingsView;
import ca.ntro.cards.foo.frontend.views.FooReplayView;
import ca.ntro.cards.foo.frontend.views.FooVariablesView;
import ca.ntro.cards.foo.frontend.views.fragments.FooTestCaseFragment;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.foo.models.FooProcedureSettingsModel;
import ca.ntro.cards.foo.frontend.views.FooCardsView;
import ca.ntro.cards.foo.frontend.views.FooSelectionsView;
import ca.ntro.cards.foo.frontend.views.FooProcedureDashboardView;

public class FooProcedureFrontend<STUDENT_MODEL extends FooCardsModel>

       extends ProcedureFrontend<FooProcedureRootView,
                                 FooProcedureSettingsView, 
                                 FooCardsView, 
                                 FooProcedureDashboardView, 
                                 FooSelectionsView,
                                 FooTestCaseFragment,
                                 FooReplayView,
                                 FooVariablesView,
                                 FooProcedureViewData, 
                                 STUDENT_MODEL, // CanvasModel
                                 FooProcedureDashboardModel, 
                                 FooProcedureSettingsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		
	}

	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<FooProcedureRootView> rootViewClass() {
		return FooProcedureRootView.class;
	}

	@Override
	protected Class<FooProcedureSettingsView> settingsViewClass() {
		return FooProcedureSettingsView.class;
	}

	@Override
	protected Class<FooCardsView> canvasViewClass() {
		return FooCardsView.class;
	}

	@Override
	protected Class<FooProcedureDashboardView> dashboardViewClass() {
		return FooProcedureDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<FooProcedureViewData> viewDataClass() {
		return FooProcedureViewData.class;
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
	protected Class<FooSelectionsView> selectionsViewClass() {
		return FooSelectionsView.class;
	}

	@Override
	protected Class<FooReplayView> replayControlsViewClass() {
		return FooReplayView.class;
	}

	@Override
	protected Class<FooVariablesView> variablesViewClass() {
		return FooVariablesView.class;
	}

	@Override
	protected void addSubTasksToCards(FrontendTasks subTasks) {
	}

	@Override
	protected Class<FooTestCaseFragment> testCaseFragmentClass() {
		return FooTestCaseFragment.class;
	}



}
