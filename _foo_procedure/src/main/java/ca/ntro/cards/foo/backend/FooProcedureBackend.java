package ca.ntro.cards.foo.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.foo.backend.tasks.FooModifyCardsModel;
import ca.ntro.cards.foo.messages.MsgManualExecutionStep;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.values.FooTestCase;
import ca.ntro.cards.foo.test_cases.FooTestCaseDatabase;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTrace;
import ca.ntro.cards.foo.models.FooProcedureDashboardModel;
import ca.ntro.cards.foo.models.FooProcedureSettingsModel;

public class   FooProcedureBackend<STUDENT_MODEL extends FooCardsModel>


       extends ProcedureBackend<FooCardsModel,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                FooTestCase,
                                FooTestCaseDatabase,
                                FooExecutionTrace,
                                FooProcedureDashboardModel,
                                FooProcedureSettingsModel> {

	
	

	@Override
	protected void addSubTasksToModifyCanvasModel(BackendTasks subTasks) {
		super.addSubTasksToModifyCanvasModel(subTasks);

		FooModifyCardsModel.updateList(subTasks, getStudentModelClass());
	}

	@Override
	protected void addSubTasksToAccessTestCaseDatabase(BackendTasks subTasks) {
	}

}
