package ca.ntro.cards.efficiency;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.common.CommonApp;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyCanvasView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyDashboardView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyRootView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;

public abstract class EfficiencyApp<CARDS_MODEL      extends EfficiencyGraphsModel,
                                    TEST_CASE        extends TestCase<CARDS_MODEL>,
                                    TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                    DASHBOARD_MODEL  extends EfficiencyDashboardModel,
                                    SETTINGS_MODEL   extends EfficiencySettingsModel,
                                                                                                      
                                    BACKEND extends EfficiencyBackend<CARDS_MODEL, 
                                                                      TEST_CASE, 
                                                                      TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL>,
                                   
                                    ROOT_VIEW       extends EfficiencyRootView, 
                                    CARDS_VIEW      extends EfficiencyCanvasView, 
                                    DASHBOARD_VIEW  extends EfficiencyDashboardView,
                                    SETTINGS_VIEW   extends EfficiencySettingsView,
                                    CARDS_VIEW_DATA extends EfficiencyViewData,
                                     
                                    FRONTEND extends EfficiencyFrontend<ROOT_VIEW, 
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
	public void registerArgs(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<CARDS_MODEL> cardsModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<TEST_CASE> testCaseClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<TEST_CASES_MODEL> testCasesModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<DASHBOARD_MODEL> dashboardModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<SETTINGS_MODEL> settingsModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerAdditionnalMessages(MessageRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected FRONTEND createFrontend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BACKEND createBackend() {
		// TODO Auto-generated method stub
		return null;
	}
}
