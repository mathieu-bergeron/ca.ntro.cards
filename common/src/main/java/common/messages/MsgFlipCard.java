package common.messages;

import ca.ntro.app.messages.MessageNtro;
import common.models.CardsModel;

public class MsgFlipCard extends MessageNtro {
	
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void applyTo(CardsModel demoModel) {

		demoModel.flipCard(cardId);

	}

}
