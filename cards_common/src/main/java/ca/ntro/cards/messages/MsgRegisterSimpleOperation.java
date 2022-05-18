package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

public abstract class MsgRegisterSimpleOperation<CARDS_MODEL extends CardsModel,
                                                 DASHBOARD_MODEL extends DashboardModel> 

       extends        MessageNtro {
	
	private CARDS_MODEL cardsModel;
	
	public CARDS_MODEL getCardsModel() {
		return cardsModel;
	}

	public void setCardsModel(CARDS_MODEL cardsModel) {
		this.cardsModel = cardsModel;
	}

	public void applyTo(CARDS_MODEL cardsModel) {
		cardsModel.copyDataFrom(this.cardsModel);
	}

	public abstract void applyTo(DASHBOARD_MODEL dashboardModel);

}
