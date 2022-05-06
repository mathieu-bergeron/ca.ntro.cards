package ca.ntro.cards.playground.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontend;
import ca.ntro.cards.playground.frontend.views.PlaygroundCardsView;
import ca.ntro.cards.playground.frontend.views.PlaygroundDashboardView;
import ca.ntro.cards.playground.frontend.views.PlaygroundRootView;
import ca.ntro.cards.playground.frontend.views.PlaygroundSettingsView;
import ca.ntro.cards.playground.frontend.views.data.PlaygroundCardsViewData;
import ca.ntro.cards.playground.models.PlaygroundCardsModel;
import ca.ntro.cards.playground.models.PlaygroundSettingsModel;

public class PlaygroundFrontend extends CommonFrontend<PlaygroundRootView,
                                                       PlaygroundSettingsView,
                                                       PlaygroundCardsView,
                                                       PlaygroundDashboardView,
                                                       PlaygroundCardsViewData,
                                                       PlaygroundSettingsModel,
                                                       PlaygroundCardsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		
	}

	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<PlaygroundRootView> rootViewClass() {
		return PlaygroundRootView.class;
	}

	@Override
	protected Class<PlaygroundSettingsView> settingsViewClass() {
		return PlaygroundSettingsView.class;
	}

	@Override
	protected Class<PlaygroundCardsView> cardsViewClass() {
		return PlaygroundCardsView.class;
	}

	@Override
	protected Class<PlaygroundDashboardView> dashboardViewClass() {
		return PlaygroundDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<PlaygroundCardsViewData> cardsViewDataClass() {
		return PlaygroundCardsViewData.class;
	}

	@Override
	protected Class<PlaygroundSettingsModel> settingsModelClass() {
		return PlaygroundSettingsModel.class;
	}

	@Override
	protected Class<PlaygroundCardsModel> cardsModelClass() {
		return PlaygroundCardsModel.class;
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
	protected void createAdditionnalTasks(FrontendTasks tasks) {
		
	}

}