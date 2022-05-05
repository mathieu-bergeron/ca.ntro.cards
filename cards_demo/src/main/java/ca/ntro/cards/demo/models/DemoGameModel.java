package ca.ntro.cards.demo.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.frontend.views.data.GameViewData;
import ca.ntro.cards.models.GameModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.Card;

public class DemoGameModel extends GameModel {
	
	private long version = 0;
	private List<Card> cards = new ArrayList<>();

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void updateViewData(GameViewData gameViewData) {
		gameViewData.removeCardsNotIn(cards);
		
		for(int i = 0; i < cards.size(); i++) {
			gameViewData.addOrUpdateCard(i, cards.get(i));
		}
	}

	public void updateCardsInOrder(List<Card> cards) {
		setCards(cards);
		version++;
	}

	public void createFirstVersion() {
		if(version == 0) {
			cards.add(new Card(1, 3, Suit.HEARTS));
			cards.add(new Card(2, 6, Suit.CLUBS));
			cards.add(new Card(3, 4, Suit.SPADES));
			cards.add(new Card(4, 10, Suit.DIAMONDS));
			cards.add(new Card(5, 5, Suit.HEARTS));
		}
	}
	
	private Card cardById(String cardId) {
		Card result = null;
		
		for(Card candidate : cards) {
			if(candidate.hasId(cardId)){
				result = candidate;
				break;
			}
		}
		
		return result;
	}

	public void flipCard(String cardId) {
		Card card = cardById(cardId);
		
		if(card != null) {
			card.flip();
		}
	}

}