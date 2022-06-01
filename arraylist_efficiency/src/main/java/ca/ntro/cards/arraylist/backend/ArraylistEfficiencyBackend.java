package ca.ntro.cards.arraylist.backend;

import ca.ntro.cards.arraylist.models.ArraylistEfficiencyDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistEfficiencySettingsModel;
import ca.ntro.cards.arraylist.models.ArraylistGraphsModel;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class ArraylistEfficiencyBackend<STUDENT_MODEL extends ListeTableau>

       extends EfficiencyBackend<ListeTableau, 
                                 STUDENT_MODEL,
                                 ArraylistGraphsModel,            // CanvasModel
                                 ArraylistTestCase,
                                 ArraylistTestCaseDatabase,
                                 ArraylistExecutionTrace,
                                 ArraylistEfficiencyDashboardModel,
                                 ArraylistEfficiencySettingsModel> {




}
