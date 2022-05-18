package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonLocalApp;
import ca.ntro.cards.demo.backend.DemoBackend;
import ca.ntro.cards.demo.frontend.DemoFrontend;
import ca.ntro.cards.demo.frontend.procedures.DemoNaiveSort;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.frontend.views.data.DemoCardsViewData;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.messages.MsgUnlockThread;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;

public abstract class   DemoLocalApp<STUDENT_MODEL extends DemoCardsModel>

                extends CommonLocalApp<STUDENT_MODEL, 
                                       DemoDashboardModel,
                                       DemoSettingsModel,
                                       DemoMsgRegisterSimpleOperation<STUDENT_MODEL>,
                                       DemoBackend<STUDENT_MODEL>,
                                       DemoRootView,
                                       DemoCardsView,
                                       DemoDashboardView,
                                       DemoSettingsView,
                                       DemoCardsViewData,
                                       DemoFrontend<STUDENT_MODEL>> {
	

	@Override
	protected Class<STUDENT_MODEL> cardsModelClass() {
		return naiveSortClass();
	}


	protected abstract Class<STUDENT_MODEL> naiveSortClass();


	@Override
	protected Class<DemoDashboardModel> dashboardModelClass() {
		return DemoDashboardModel.class;
	}


	@Override
	protected Class<DemoSettingsModel> settingsModelClass() {
		return DemoSettingsModel.class;
	}


	@Override
	protected Class<? extends MsgRegisterSimpleOperation> msgRegisterSimpleOperationClass() {
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
		registrar.registerValue(DemoNaiveSort.class);
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgUpdateList.class);
		registrar.registerMessage(MsgUnlockThread.class);
	}


}
