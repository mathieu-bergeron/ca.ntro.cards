package ca.ntro.cards.foo.backend;

import ca.ntro.cards.foo.models.FooEfficiencyDashboardModel;
import ca.ntro.cards.foo.models.FooEfficiencySettingsModel;
import ca.ntro.cards.foo.models.FooGraphsModel;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.values.FooTestCase;
import ca.ntro.cards.foo.test_cases.FooTestCaseDatabase;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class FooEfficiencyBackend<STUDENT_MODEL extends FooCardsModel>

       extends EfficiencyBackend<FooCardsModel, 
                                 STUDENT_MODEL,
                                 FooGraphsModel,            // CanvasModel
                                 FooTestCase,
                                 FooTestCaseDatabase,
                                 FooExecutionTrace,
                                 FooEfficiencyDashboardModel,
                                 FooEfficiencySettingsModel> {




}
