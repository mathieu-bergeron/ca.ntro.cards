package ca.ntro.cards.demo.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.demo.models.DemoCardsModel;

public class MsgUpdateList extends MessageNtro {
	
	List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void applyTo(DemoCardsModel demoModel) {
		demoModel.updateCardsInOrder(cards);
	}
	
	

}
