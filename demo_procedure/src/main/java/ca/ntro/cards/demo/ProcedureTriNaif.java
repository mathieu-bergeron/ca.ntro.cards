package ca.ntro.cards.demo;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.demo.backend.DemoBackend;
import ca.ntro.cards.demo.frontend.DemoFrontend;
import ca.ntro.cards.demo.frontend.DemoViewData;
import ca.ntro.cards.demo.frontend.views.DemoCardsView;
import ca.ntro.cards.demo.frontend.views.DemoDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoRootView;
import ca.ntro.cards.demo.frontend.views.DemoSettingsView;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.demo.models.DemoSettingsModel;
import ca.ntro.cards.demo.models.DemoTestCasesModel;

public abstract class   ProcedureTriNaif<STUDENT_MODEL extends TriNaif>

                extends ProcedureApp<TriNaif, 
                                  DemoTestCase,
                                  DemoTestCasesModel,
                                  DemoDashboardModel,
                                  DemoSettingsModel,
                                  DemoBackend,
                                  DemoRootView,
                                  DemoCardsView,
                                  DemoDashboardView,
                                  DemoSettingsView,
                                  DemoViewData,
                                  DemoFrontend> {

                                    	   
    private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class<TriNaif> cardsModelClass() {
		return (Class<TriNaif>) classeTriNaif();
	}


	protected abstract Class<STUDENT_MODEL> classeTriNaif();

	@Override
	protected Class<DemoTestCase> testCaseClass() {
		return DemoTestCase.class;
	}

	@Override
	protected Class<DemoTestCasesModel> testCasesModelClass() {
		return DemoTestCasesModel.class;
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
