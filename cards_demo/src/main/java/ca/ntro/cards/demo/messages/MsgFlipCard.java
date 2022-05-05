package ca.ntro.cards.demo.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.demo.models.DemoModel;

public class MsgFlipCard extends MessageNtro {
	
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void applyTo(DemoModel demoModel) {

		demoModel.flipCard(cardId);

	}

}
