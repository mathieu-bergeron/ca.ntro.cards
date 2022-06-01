package ca.ntro.cards.arraylist.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.arraylist.messages.ArraylistMsgAcceptManualModel;
import ca.ntro.cards.arraylist.models.ArraylistCardsModel;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureSettingsModel;

public class   ArraylistProcedureBackend<STUDENT_MODEL extends ArraylistCardsModel>


       extends ProcedureBackend<ArraylistCardsModel,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                ArraylistTestCase,
                                ArraylistTestCaseDatabase,
                                ArraylistExecutionTrace,
                                ArraylistProcedureDashboardModel,
                                ArraylistProcedureSettingsModel,
                                ArraylistMsgAcceptManualModel> {

	
	


}
