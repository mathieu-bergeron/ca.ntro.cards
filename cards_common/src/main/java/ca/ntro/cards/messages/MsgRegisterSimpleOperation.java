package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

public abstract class MsgRegisterSimpleOperation<CARDS_MODEL extends CardsModel,
                                                 DASHBOARD_MODEL extends DashboardModel> 

       extends        MessageNtro {
	
	public abstract void applyTo(CARDS_MODEL cardsModel);

	public abstract void applyTo(DASHBOARD_MODEL dashboardModel);

}
