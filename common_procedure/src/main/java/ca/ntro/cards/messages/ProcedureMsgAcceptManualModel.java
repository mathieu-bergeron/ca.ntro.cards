package ca.ntro.cards.messages;


import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.ProcedureDashboardModel;
import ca.ntro.cards.test_cases.ProcedureTestCaseDatabase;

public class ProcedureMsgAcceptManualModel<CARDS_MODEL        extends ProcedureCardsModel,
                                           DASHBOARD_MODEL    extends ProcedureDashboardModel,
                                           TEST_CASE_DATABASE extends ProcedureTestCaseDatabase> 


       extends MessageNtro {
	
	CARDS_MODEL manualModel;

	public CARDS_MODEL getManualModel() {
		return manualModel;
	}

	public void setManualModel(CARDS_MODEL manualModel) {
		this.manualModel = manualModel;
	}

	public void applyTo(CARDS_MODEL cardsModel, 
			            DASHBOARD_MODEL dashboardModel, 
			            TEST_CASE_DATABASE testCaseDatabase) {

		boolean modified = cardsModel.acceptManualModel(manualModel);
		
		if(modified) {
			testCaseDatabase.pushManualExecutionStep(cardsModel);
			testCaseDatabase.updateDashboardModel(dashboardModel);
		}
	}
}
