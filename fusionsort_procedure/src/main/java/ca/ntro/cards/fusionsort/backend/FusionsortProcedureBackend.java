package ca.ntro.cards.fusionsort.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.common.messages.MsgRefreshDashboard;
import ca.ntro.cards.fusionsort.messages.FusionsortMsgAcceptManualModel;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureSettingsModel;

public class   FusionsortProcedureBackend<STUDENT_MODEL extends TriFusion>


       extends ProcedureBackend<TriFusion,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                FusionsortTestCase,
                                FusionsortTestCaseDatabase,
                                FusionsortExecutionTrace,
                                FusionsortProcedureDashboardModel,
                                FusionsortProcedureSettingsModel,
                                FusionsortMsgAcceptManualModel> {
	


}
