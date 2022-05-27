package ca.ntro.cards.demo.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.messages.MsgManualExecutionStep;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.demo.test_cases.execution_trace.DemoExecutionTrace;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;

public class   DemoProcedureBackend<STUDENT_MODEL extends DemoCardsModel>


       extends ProcedureBackend<DemoCardsModel,       // executable model
                                STUDENT_MODEL,
                                STUDENT_MODEL, // canvas model
                                DemoTestCase,
                                DemoTestCaseDatabase,
                                DemoExecutionTrace,
                                DemoProcedureDashboardModel,
                                DemoProcedureSettingsModel> {

	
	

	@Override
	protected void addSubTasksToModifyCanvasModel(BackendTasks subTasks) {
		super.addSubTasksToModifyCanvasModel(subTasks);

		DemoModifyCardsModel.updateList(subTasks, getStudentModelClass());
	}

	@Override
	protected void addSubTasksToAccessTestCaseDatabase(BackendTasks subTasks) {
	}

}
