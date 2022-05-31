package ca.ntro.cards.freesort.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.freesort.backend.tasks.FreesortModifyCardsModel;
import ca.ntro.cards.freesort.messages.MsgManualExecutionStep;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.freesort.models.FreesortProcedureDashboardModel;
import ca.ntro.cards.freesort.models.FreesortProcedureSettingsModel;

public class   FreesortProcedureBackend<STUDENT_MODEL extends TriLibre>


       extends ProcedureBackend<TriLibre,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                FreesortTestCase,
                                FreesortTestCaseDatabase,
                                FreesortExecutionTrace,
                                FreesortProcedureDashboardModel,
                                FreesortProcedureSettingsModel> {

	
	

	@Override
	protected void addSubTasksToModifyCanvasModel(BackendTasks subTasks) {
		super.addSubTasksToModifyCanvasModel(subTasks);

		FreesortModifyCardsModel.updateList(subTasks, getStudentModelClass());
	}

	@Override
	protected void addSubTasksToAccessTestCaseDatabase(BackendTasks subTasks) {
	}

}
