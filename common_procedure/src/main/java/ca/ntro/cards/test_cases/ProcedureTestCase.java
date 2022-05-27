package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;

public class   ProcedureTestCase<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                 STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                 DASHBOARD_MODEL  extends ProcedureDashboardModel> 

       extends CommonTestCase<EXECUTABLE_MODEL, STUDENT_MODEL, DASHBOARD_MODEL> {

	public void updateCardsModel(EXECUTABLE_MODEL cardsModel) {
		executionTraceByMode(Mode.MANUAL).copyDataTo(cardsModel);
	}

	public void updateDashboardModel(DASHBOARD_MODEL dashboardModel) {
		executionTraceByMode(Mode.MANUAL).updateDashboardModel(dashboardModel);
	}

}
