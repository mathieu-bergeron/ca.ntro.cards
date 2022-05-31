package ca.ntro.cards;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.common.CommonApp;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.messages.MsgToggleUseFourCardColors;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.frontend.views.ProcedureCanvasView;
import ca.ntro.cards.frontend.views.ProcedureDashboardView;
import ca.ntro.cards.frontend.views.ProcedureRootView;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.cards.messages.MsgChangeCurrentTestCase;
import ca.ntro.cards.messages.MsgExecutionStepBack;
import ca.ntro.cards.messages.MsgExecutionStepForward;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.models.ProcedureSettingsModel;
import ca.ntro.cards.test_cases.ProcedureTestCase;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTraceFull;

public abstract class ProcedureApp<EXECUTABLE_MODEL     extends ProcedureCardsModel,
							       STUDENT_MODEL        extends EXECUTABLE_MODEL,
	                               CANVAS_MODEL         extends ProcedureCardsModel,
                                   TEST_CASE            extends ProcedureTestCase,
                                   TEST_CASE_DESCRIPTOR extends ProcedureTestCaseDescriptor,
                                   TEST_CASES_MODEL     extends ProcedureTestCaseDatabase,
                                   EXECUTION_TRACE      extends ProcedureExecutionTrace,
                                   DASHBOARD_MODEL      extends ProcedureDashboardModel,
                                   SETTINGS_MODEL       extends ProcedureSettingsModel,
                                                                                                      
                                   BACKEND extends CommonBackend<EXECUTABLE_MODEL, 
                                                                 STUDENT_MODEL,
                                                                 CANVAS_MODEL,
                                                                 TEST_CASE, 
                                                                 TEST_CASES_MODEL, 
                                                                 EXECUTION_TRACE,
                                                                 DASHBOARD_MODEL, 
                                                                 SETTINGS_MODEL>,
                                   
                                   ROOT_VIEW       extends ProcedureRootView, 
                                   CANVAS_VIEW     extends ProcedureCanvasView, 
                                   DASHBOARD_VIEW  extends ProcedureDashboardView,
                                   SETTINGS_VIEW   extends ProcedureSettingsView,
                                   VIEW_DATA       extends ProcedureViewData,
                                     
                                   FRONTEND extends CommonFrontend<ROOT_VIEW, 
                                                                   SETTINGS_VIEW, 
                                                                   CANVAS_VIEW, 
                                                                   DASHBOARD_VIEW, 
                                                                   VIEW_DATA,
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
                         CANVAS_VIEW,
                         DASHBOARD_VIEW,
                         SETTINGS_VIEW,
                         VIEW_DATA,
                         FRONTEND> {
                                                                	 
    protected abstract Class<TEST_CASE_DESCRIPTOR> testCaseDescriptorClass();

	@Override
	public void registerModels(ModelRegistrar registrar) {
		super.registerModels(registrar);
		
		registrar.registerValue(testCaseDescriptorClass());
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
		super.registerMessages(registrar);

		registrar.registerMessage(MsgToggleUseFourCardColors.class);
		registrar.registerMessage(MsgExecutionStepBack.class);
		registrar.registerMessage(MsgExecutionStepForward.class);
		registrar.registerMessage(MsgChangeCurrentTestCase.class);

	}

	@Override
	protected Class<? extends EXECUTION_TRACE> executionTraceClass() {
		return (Class<? extends EXECUTION_TRACE>) ProcedureExecutionTraceFull.class;
	}

	


}
