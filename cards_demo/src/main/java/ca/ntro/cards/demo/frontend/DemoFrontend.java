package ca.ntro.cards.demo.frontend;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.frontend.CommonFrontend;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.frontend.views.data.DemoCardsViewData;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;

public class DemoFrontend extends CommonFrontend<DemoRootView,
                                                 DemoSettingsView,
                                                 DemoCardsView,
                                                 DemoDashboardView,
                                                 DemoCardsViewData,
                                                 DemoCardsModel,
                                                 DemoDashboardModel,
                                                 DemoSettingsModel,
                                                 DemoMsgRegisterSimpleOperation> {

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
	protected Class<DemoCardsView> cardsViewClass() {
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
	protected Class<DemoCardsViewData> cardsViewDataClass() {
		return DemoCardsViewData.class;
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
