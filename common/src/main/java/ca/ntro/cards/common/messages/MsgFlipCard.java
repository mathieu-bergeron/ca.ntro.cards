package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.CommonCardsModel;

public class MsgFlipCard extends MessageNtro {
	
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void applyTo(CommonCardsModel demoModel) {

		demoModel.flipCard(cardId);

	}

}
