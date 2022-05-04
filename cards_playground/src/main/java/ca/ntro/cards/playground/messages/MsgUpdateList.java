package ca.ntro.cards.playground.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.playground.models.PlaygroundModel;

public class MsgUpdateList extends MessageNtro {
	
	List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void applyTo(PlaygroundModel playgroundModel) {
		playgroundModel.updateList(cards);
	}
	
	

}
