package ca.ntro.cards.common;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.frontend.views.CommonRootView;
import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonSettingsModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.TestCase;

public abstract class CommonApp<CARDS_MODEL      extends CommonCanvasModel,
                                TEST_CASE        extends TestCase<CARDS_MODEL>,
                                TEST_CASES_MODEL extends TestCasesModel<CARDS_MODEL, TEST_CASE>,
                                DASHBOARD_MODEL  extends CommonDashboardModel,
                                SETTINGS_MODEL   extends CommonSettingsModel,
                                                                                                      
                                BACKEND extends CommonBackend<CARDS_MODEL, 
                                                              TEST_CASE, 
                                                              TEST_CASES_MODEL, DASHBOARD_MODEL, SETTINGS_MODEL>,
                                   
                                ROOT_VIEW       extends CommonRootView, 
                                CARDS_VIEW      extends CommonCanvasView, 
                                DASHBOARD_VIEW  extends CommonDashboardView,
                                SETTINGS_VIEW   extends CommonSettingsView,
                                CARDS_VIEW_DATA extends CommonViewData,
                                     
                                FRONTEND extends CommonFrontend<ROOT_VIEW, 
                                                                SETTINGS_VIEW, 
                                                                CARDS_VIEW, 
                                                                DASHBOARD_VIEW, 
                                                                CARDS_VIEW_DATA,
                                                                CARDS_MODEL,
                                                                DASHBOARD_MODEL,
                                                                SETTINGS_MODEL>>

                implements NtroClientFx {


}
