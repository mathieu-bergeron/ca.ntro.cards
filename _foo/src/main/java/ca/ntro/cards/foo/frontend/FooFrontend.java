package ca.ntro.cards.foo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontend;
import ca.ntro.cards.foo.frontend.views.FooRootView;
import ca.ntro.cards.foo.frontend.views.FooSettingsView;
import ca.ntro.cards.foo.frontend.views.data.FooCardsViewData;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.FooSettingsModel;
import ca.ntro.cards.foo.frontend.views.FooCardsView;
import ca.ntro.cards.foo.frontend.views.FooDashboardView;

public class FooFrontend extends CommonFrontend<FooRootView,
                                                 FooSettingsView,
                                                 FooCardsView,
                                                 FooDashboardView,
                                                 FooCardsViewData,
                                                 FooSettingsModel,
                                                 FooCardsModel> {

	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		
	}

	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<FooRootView> rootViewClass() {
		return FooRootView.class;
	}

	@Override
	protected Class<FooSettingsView> settingsViewClass() {
		return FooSettingsView.class;
	}

	@Override
	protected Class<FooCardsView> cardsViewClass() {
		return FooCardsView.class;
	}

	@Override
	protected Class<FooDashboardView> dashboardViewClass() {
		return FooDashboardView.class;
	}

	@Override
	protected void registerAdditionnalViews(ViewRegistrarFx registrar) {
		
	}

	@Override
	protected Class<FooCardsViewData> cardsViewDataClass() {
		return FooCardsViewData.class;
	}

	@Override
	protected Class<FooSettingsModel> settingsModelClass() {
		return FooSettingsModel.class;
	}

	@Override
	protected Class<FooCardsModel> cardsModelClass() {
		return FooCardsModel.class;
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
}
