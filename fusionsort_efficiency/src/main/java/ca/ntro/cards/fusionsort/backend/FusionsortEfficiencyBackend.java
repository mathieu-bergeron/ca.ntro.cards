package ca.ntro.cards.fusionsort.backend;

import ca.ntro.cards.fusionsort.models.FusionsortEfficiencyDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortEfficiencySettingsModel;
import ca.ntro.cards.fusionsort.models.FusionsortGraphsModel;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class FusionsortEfficiencyBackend<STUDENT_MODEL extends TriFusion>

       extends EfficiencyBackend<TriFusion, 
                                 STUDENT_MODEL,
                                 FusionsortGraphsModel,            // CanvasModel
                                 FusionsortTestCase,
                                 FusionsortTestCaseDatabase,
                                 FusionsortExecutionTrace,
                                 FusionsortEfficiencyDashboardModel,
                                 FusionsortEfficiencySettingsModel> {




}
