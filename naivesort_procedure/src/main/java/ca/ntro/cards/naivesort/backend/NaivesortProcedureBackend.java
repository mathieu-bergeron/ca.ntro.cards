package ca.ntro.cards.naivesort.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.naivesort.messages.NaivesortMsgAcceptManualModel;
import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.naivesort.models.NaivesortProcedureDashboardModel;
import ca.ntro.cards.naivesort.models.NaivesortProcedureSettingsModel;

public class   NaivesortProcedureBackend<STUDENT_MODEL extends TriNaif>


       extends ProcedureBackend<TriNaif,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                NaivesortTestCase,
                                NaivesortTestCaseDatabase,
                                NaivesortExecutionTrace,
                                NaivesortProcedureDashboardModel,
                                NaivesortProcedureSettingsModel,
                                NaivesortMsgAcceptManualModel> {

	@Override
	protected void addSubTasksToAccessTestCaseDatabase(BackendTasks subTasks) {
	}

}
