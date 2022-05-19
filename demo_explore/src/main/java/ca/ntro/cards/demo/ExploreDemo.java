package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.CommonApp;
import ca.ntro.cards.demo.backend.DemoBackend;
import ca.ntro.cards.demo.frontend.DemoFrontend;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.frontend.views.data.DemoCardsViewData;
import ca.ntro.cards.demo.messages.DemoMsgRegisterSimpleOperation;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.NaiveSort;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;

public abstract class   ExploreDemo<STUDENT_MODEL extends NaiveSort>

                extends CommonApp<STUDENT_MODEL, 
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
                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

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
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		registrar.registerMessage(MsgUpdateList.class);
	}


}
