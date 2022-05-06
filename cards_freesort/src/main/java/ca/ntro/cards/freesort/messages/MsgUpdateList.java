package ca.ntro.cards.freesort.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.freesort.models.FreesortCardsModel;

public class MsgUpdateList extends MessageNtro {
	
	List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void applyTo(FreesortCardsModel freesortModel) {
		freesortModel.updateCardsInOrder(cards);
	}
	
	

}
