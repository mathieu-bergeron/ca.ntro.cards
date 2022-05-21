package ca.ntro.cards.demo;


import ca.ntro.cards.demo.backend.DemoEfficiencyBackend;
import ca.ntro.cards.demo.frontend.DemoEfficiencyFrontend;
import ca.ntro.cards.demo.frontend.DemoEfficiencyViewData;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyDashboardView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencyRootView;
import ca.ntro.cards.demo.frontend.views.DemoEfficiencySettingsView;
import ca.ntro.cards.demo.frontend.views.DemoGraphsView;
import ca.ntro.cards.demo.models.DemoEfficiencyDashboardModel;
import ca.ntro.cards.demo.models.DemoEfficiencySettingsModel;
import ca.ntro.cards.demo.models.DemoGraphsModel;
import ca.ntro.cards.demo.models.DemoTestCasesModel;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.efficiency.EfficiencyApp;

public abstract class   EfficaciteTriNaif<STUDENT_MODEL extends TriNaif>

                extends EfficiencyApp<TriNaif, 
                                      DemoGraphsModel,
                                      DemoTestCase,
                                      DemoTestCasesModel,
                                      DemoEfficiencyDashboardModel,
                                      DemoEfficiencySettingsModel,
                                      DemoEfficiencyBackend,
                                      DemoEfficiencyRootView,
                                      DemoGraphsView,
                                      DemoEfficiencyDashboardView,
                                      DemoEfficiencySettingsView,
                                      DemoEfficiencyViewData,
                                      DemoEfficiencyFrontend> {


}
