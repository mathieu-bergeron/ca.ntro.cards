package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.GameModel;

public class MsgFlipCard extends MessageNtro {
	
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void applyTo(GameModel demoModel) {

		demoModel.flipCard(cardId);

	}

}
