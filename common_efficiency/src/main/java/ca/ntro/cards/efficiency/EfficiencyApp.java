package ca.ntro.cards.efficiency;

import ca.ntro.cards.common.CommonApp;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.common.test_cases.CommonTestCaseDatabase;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;
import ca.ntro.cards.efficiency.frontend.EfficiencyViewData;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyGraphsView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyDashboardView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencyRootView;
import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;
import ca.ntro.cards.efficiency.test_cases.execution_trace.EfficiencyExecutionTraceSizeOnly;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public abstract class EfficiencyApp<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                    STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                    CANVAS_MODEL     extends EfficiencyGraphsModel,
                                    TEST_CASE        extends CommonTestCase,
                                    TEST_CASES_MODEL extends CommonTestCaseDatabase,
                                    EXECUTION_TRACE  extends ProcedureExecutionTrace,
                                    DASHBOARD_MODEL  extends EfficiencyDashboardModel,
                                    SETTINGS_MODEL   extends EfficiencySettingsModel,
                                                                                                      
                                    BACKEND extends EfficiencyBackend<EXECUTABLE_MODEL,
                                                                      STUDENT_MODEL,
                                                                      CANVAS_MODEL, 
                                                                      TEST_CASE, 
                                                                      TEST_CASES_MODEL, 
                                                                      EXECUTION_TRACE,
                                                                      DASHBOARD_MODEL, 
                                                                      SETTINGS_MODEL>,
                                   
                                    ROOT_VIEW       extends EfficiencyRootView, 
                                    CARDS_VIEW      extends EfficiencyGraphsView, 
                                    DASHBOARD_VIEW  extends EfficiencyDashboardView,
                                    SETTINGS_VIEW   extends EfficiencySettingsView,
                                    CARDS_VIEW_DATA extends EfficiencyViewData,
                                     
                                    FRONTEND extends EfficiencyFrontend<ROOT_VIEW, 
                                                                        SETTINGS_VIEW, 
                                                                        CARDS_VIEW, 
                                                                        DASHBOARD_VIEW, 
                                                                        CARDS_VIEW_DATA,
                                                                        CANVAS_MODEL,
                                                                        DASHBOARD_MODEL,
                                                                        SETTINGS_MODEL>>

               extends CommonApp<EXECUTABLE_MODEL,
                                 STUDENT_MODEL,
                                 CANVAS_MODEL,
                                 TEST_CASE,
                                 TEST_CASES_MODEL,
                                 EXECUTION_TRACE,
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
	protected Class<? extends EXECUTION_TRACE> executionTraceClass() {
		return (Class<? extends EXECUTION_TRACE>) EfficiencyExecutionTraceSizeOnly.class;
	}

    @Override
	protected void additionnalBackendInitialization(BACKEND backend) {
    }


}
