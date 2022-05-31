package ca.ntro.cards.freesort.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.freesort.models.FreesortEfficiencyDashboardModel;
import ca.ntro.cards.freesort.models.FreesortEfficiencySettingsModel;
import ca.ntro.cards.freesort.models.FreesortGraphsModel;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class FreesortEfficiencyBackend<STUDENT_MODEL extends TriLibre>

       extends EfficiencyBackend<TriLibre, 
                                 STUDENT_MODEL,
                                 FreesortGraphsModel,            // CanvasModel
                                 FreesortTestCase,
                                 FreesortTestCaseDatabase,
                                 FreesortExecutionTrace,
                                 FreesortEfficiencyDashboardModel,
                                 FreesortEfficiencySettingsModel> {



	@Override
	protected void addSubTasksToModifyCanvasModel(BackendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToModifyDashboardModel(BackendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {
		
	}


	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		
	}

}
