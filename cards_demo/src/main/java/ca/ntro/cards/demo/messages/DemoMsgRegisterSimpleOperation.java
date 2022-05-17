package ca.ntro.cards.demo.messages;

import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;

public class DemoMsgRegisterSimpleOperation extends MsgRegisterSimpleOperation<DemoCardsModel, DemoDashboardModel> {

	@Override
	public void applyTo(DemoCardsModel cardsModel) {
		
	}

	@Override
	public void applyTo(DemoDashboardModel dashboardModel) {
		
	}

}
