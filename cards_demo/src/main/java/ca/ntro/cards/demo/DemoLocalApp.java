package ca.ntro.cards.demo;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonLocalApp;
import ca.ntro.cards.demo.backend.DemoBackend;
import ca.ntro.cards.demo.frontend.DemoFrontend;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.frontend.views.data.DemoCardsViewData;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;

public class DemoLocalApp extends CommonLocalApp<DemoCardsModel, 
                                                 DemoDashboardModel,
                                                 DemoSettingsModel,
                                                 DemoMsgRegisterSimpleOperation,
                                                 DemoBackend,
                                                 DemoRootView,
                                                 DemoCardsView,
                                                 DemoDashboardView,
                                                 DemoSettingsView,
                                                 DemoCardsViewData,
                                                 DemoFrontend> {
	
	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<DemoCardsModel> cardsModelClass() {
		return DemoCardsModel.class;
	}


	@Override
	protected Class<DemoDashboardModel> dashboardModelClass() {
		return DemoDashboardModel.class;
	}


	@Override
	protected Class<DemoSettingsModel> settingsModelClass() {
		return DemoSettingsModel.class;
	}


	@Override
	protected Class<DemoMsgRegisterSimpleOperation> msgRegisterSimpleOperationClass() {
		return DemoMsgRegisterSimpleOperation.class;
	}


	@Override
	protected DemoFrontend createFrontend() {
		return new DemoFrontend();
	}


	@Override
	protected DemoBackend createBackend() {
		return new DemoBackend();
	}


	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {

	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgUpdateList.class);
	}



}
