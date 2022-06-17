package ca.ntro.cards.intro.backend;

import ca.ntro.cards.intro.models.IntroEfficiencyDashboardModel;
import ca.ntro.cards.intro.models.IntroEfficiencySettingsModel;
import ca.ntro.cards.intro.models.IntroGraphsModel;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.efficiency.backend.EfficiencyBackend;

public class IntroEfficiencyBackend<STUDENT_MODEL extends Intro>

       extends EfficiencyBackend<Intro, 
                                 STUDENT_MODEL,
                                 IntroGraphsModel,            // CanvasModel
                                 IntroTestCase,
                                 IntroTestCaseDatabase,
                                 IntroExecutionTrace,
                                 IntroEfficiencyDashboardModel,
                                 IntroEfficiencySettingsModel> {




}
