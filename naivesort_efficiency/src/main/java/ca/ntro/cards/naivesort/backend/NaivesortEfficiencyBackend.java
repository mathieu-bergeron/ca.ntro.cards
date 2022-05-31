package ca.ntro.cards.naivesort.backend;

import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencyDashboardModel;
import ca.ntro.cards.naivesort.models.NaivesortEfficiencySettingsModel;
import ca.ntro.cards.naivesort.models.NaivesortGraphsModel;
import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;
import ca.ntro.cards.test_cases.execution_trace.ProcedureExecutionTrace;

public class NaivesortEfficiencyBackend<STUDENT_MODEL extends TriNaif>

       extends EfficiencyBackend<TriNaif, 
                                 STUDENT_MODEL,
                                 NaivesortGraphsModel,            // CanvasModel
                                 NaivesortTestCase,
                                 NaivesortTestCaseDatabase,
                                 NaivesortExecutionTrace,
                                 NaivesortEfficiencyDashboardModel,
                                 NaivesortEfficiencySettingsModel> {



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
