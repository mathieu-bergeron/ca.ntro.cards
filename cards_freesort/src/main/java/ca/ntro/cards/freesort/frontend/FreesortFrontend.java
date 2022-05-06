package ca.ntro.cards.freesort.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontend;
import ca.ntro.cards.freesort.frontend.views.FreesortRootView;
import ca.ntro.cards.freesort.frontend.views.FreesortSettingsView;
import ca.ntro.cards.freesort.frontend.views.data.FreesortCardsViewData;
import ca.ntro.cards.freesort.models.FreesortCardsModel;
import ca.ntro.cards.freesort.models.FreesortSettingsModel;
import ca.ntro.cards.freesort.frontend.views.FreesortCardsView;
import ca.ntro.cards.freesort.frontend.views.FreesortDashboardView;

public class FreesortFrontend extends CommonFrontend<FreesortRootView,
                                                 FreesortSettingsView,
                                                 FreesortCardsView,
                                                 FreesortDashboardView,
                                                 FreesortCardsViewData,
                                                 FreesortSettingsModel,
                                                 FreesortCardsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		
	}

	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<FreesortRootView> rootViewClass() {
		return FreesortRootView.class;
	}

	@Override
	protected Class<FreesortSettingsView> settingsViewClass() {
		return FreesortSettingsView.class;
	}

	@Override
	protected Class<FreesortCardsView> cardsViewClass() {
		return FreesortCardsView.class;
	}

	@Override
	protected Class<FreesortDashboardView> dashboardViewClass() {
		return FreesortDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<FreesortCardsViewData> cardsViewDataClass() {
		return FreesortCardsViewData.class;
	}

	@Override
	protected Class<FreesortSettingsModel> settingsModelClass() {
		return FreesortSettingsModel.class;
	}

	@Override
	protected Class<FreesortCardsModel> cardsModelClass() {
		return FreesortCardsModel.class;
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
