package ca.ntro.cards.efficiency.backend;

import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.cards.efficiency.models.EfficiencyDashboardModel;
import ca.ntro.cards.efficiency.models.EfficiencyGraphsModel;
import ca.ntro.cards.efficiency.models.EfficiencySettingsModel;
import ca.ntro.cards.models.ProcedureCardsModel;

public abstract class EfficiencyBackend <EXECUTABLE_MODEL extends ProcedureCardsModel,
                                         CANVAS_MODEL     extends EfficiencyGraphsModel,
                                         TEST_CASE        extends TestCase<EXECUTABLE_MODEL>,
                                         TEST_CASES_MODEL extends TestCasesModel<EXECUTABLE_MODEL, TEST_CASE>,
                                         DASHBOARD_MODEL  extends EfficiencyDashboardModel,
                                         SETTINGS_MODEL   extends EfficiencySettingsModel>

       extends        CommonBackend<EXECUTABLE_MODEL,
                                    CANVAS_MODEL, 
                                    TEST_CASE, 
                                    TEST_CASES_MODEL, 
                                    DASHBOARD_MODEL, 
                                    SETTINGS_MODEL> {


}
