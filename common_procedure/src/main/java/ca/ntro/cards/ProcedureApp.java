package ca.ntro.cards;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.common.CommonApp;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.messages.MsgExecutionEnded;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.frontend.views.ProcedureCanvasView;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureRootView;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.messages.MsgFlipCard;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.cards.models.values.Card;

public abstract class ProcedureApp<CARDS_MODEL      extends ProcedureCardsModel,
                                   TEST_CASE        extends TestCase<CARDS_MODEL>,
                                   TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                   DASHBOARD_MODEL  extends ProcedureDashboardModel,
                                   SETTINGS_MODEL   extends ProcedureSettingsModel,
                                                                                                      
                                   BACKEND extends CommonBackend<CARDS_MODEL, 
                                                                 TEST_CASE, 
                                                                 TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL>,
                                   
                                   ROOT_VIEW       extends ProcedureRootView, 
                                   CARDS_VIEW      extends ProcedureCanvasView, 
                                   DASHBOARD_VIEW  extends ProcedureDashboardView,
                                   SETTINGS_VIEW   extends ProcedureSettingsView,
                                   CARDS_VIEW_DATA extends ProcedureViewData,
                                     
                                   FRONTEND extends CommonFrontend<ROOT_VIEW, 
                                                                   SETTINGS_VIEW, 
                                                                   CARDS_VIEW, 
                                                                   DASHBOARD_VIEW, 
                                                                   CARDS_VIEW_DATA,
                                                                   CARDS_MODEL,
                                                                   DASHBOARD_MODEL,
                                                                   SETTINGS_MODEL>>

       extends CommonApp<CARDS_MODEL,
                         TEST_CASE,
                         TEST_CASES_MODEL,
                         DASHBOARD_MODEL,
                         SETTINGS_MODEL,
                         BACKEND,
                         ROOT_VIEW,
                         CARDS_VIEW,
                         DASHBOARD_VIEW,
                         SETTINGS_VIEW,
                         CARDS_VIEW_DATA,
                         FRONTEND> {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		super.registerModels(registrar);

		registrar.registerValue(Card.class);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		super.registerMessages(registrar);

		registrar.registerMessage(MsgFlipCard.class);
		registrar.registerMessage(MsgToggleUseFourCardColors.class);
		registrar.registerMessage(MsgExecutionEnded.class);
		registrar.registerMessage(MsgExecutionStepBack.class);
		registrar.registerMessage(MsgExecutionStepForward.class);

	}

	


}
