package ca.ntro.cards.demo.messages;

import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;

public class DemoMsgRegisterSimpleOperation<STUDENT_MODEL extends DemoCardsModel> extends MsgRegisterSimpleOperation<STUDENT_MODEL, DemoDashboardModel> {
	
	@Override
	public void applyTo(DemoDashboardModel dashboardModel) {
		dashboardModel.incrementSimpleOperations();
	}

}
